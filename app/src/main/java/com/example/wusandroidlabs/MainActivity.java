package com.example.wusandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wusandroidlabs.data.MainActivityViewModel;
import com.example.wusandroidlabs.databinding.ActivityMainBinding;
import com.example.wusandroidlabs.databinding.ReceiveMessageBinding;
import com.example.wusandroidlabs.databinding.SentMessageBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {


    //MutableLiveData<ArrayList<ChatMessage>> theWords;
    // protected MutableLiveData<ArrayList<ChatMessage>> theWords ;

    protected MutableLiveData<List<ChatMessage>> theWords;
    MainActivityViewModel MainModel;
    /**
     * This holds the "Click me" button
     */
    protected Button myButton;
    protected Button myButton2;

    protected RecyclerView recyclerView;

    /**
     * This holds the edit text for typing into
     */
    protected EditText theEditText;

    RecyclerView.Adapter myAdapter;
    //equivalent to        static void main(String args[])

    //MessageDatabase db ;
    ChatMessageDAO mDAO;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        super.onCreateOptionsMenu(menu);

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //calling onCreate from parent class

        MainModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        //loads an XML file on the page
        setContentView(binding.getRoot());


        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(binding.toolbar);

        if (theWords != null && theWords.getValue() == null) {

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() -> {
                List<ChatMessage> allMessages = mDAO.getAllMessages();
                theWords.getValue().addAll(allMessages);
            });
        }
        //if (theWords != null && theWords.getValue() == null) {
        //
        //            theWords.setValue(new ArrayList<>());Executor thread = Executors.newSingleThreadExecutor();
        // thread.execute(() ->
        //  {
        //theWords.getValue().addAll(mDAO.getAllMessages());

        //  runOnUiThread( () ->  binding.theRecycleView.setAdapter( myAdapter ));
        //});
        // }


        MainModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
//all new messages:

        theWords = MainModel.messages;
        myButton = binding.button;
        myButton2 = binding.button2;

        theEditText = binding.theEditText;
        recyclerView = binding.theRecycleView;
        recyclerView.setAdapter(myAdapter);

        myButton.setOnClickListener(click -> {
            String input = theEditText.getText().toString();

            int type = 0;
            SimpleDateFormat sdf = new SimpleDateFormat(" hh:mm a");
            String currentDateandTime = sdf.format(new Date());

            List<ChatMessage> currentMessages = theWords.getValue();

            if (currentMessages == null) {
                currentMessages = new ArrayList<>();
            }

            //ChatMessage chatMessage = new ChatMessage(input, currentDateandTime, true);
            ChatMessage chatMessage = new ChatMessage(input, currentDateandTime, true);

            currentMessages.add(chatMessage);

            theWords.setValue(currentMessages);
            myAdapter.notifyDataSetChanged();

            theEditText.setText("");
        });


        myButton2.setOnClickListener(click -> {
            String input = theEditText.getText().toString();


            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            String currentDateandTime = sdf.format(new Date());
            List<ChatMessage> currentMessages = theWords.getValue();
            if (currentMessages == null) {
                currentMessages = new ArrayList<>();
            }
            ChatMessage chatMessage = new ChatMessage(input, currentDateandTime, false);
            currentMessages.add(chatMessage);
            theWords.setValue(currentMessages);
            myAdapter.notifyDataSetChanged();

            theEditText.setText("");
        });


        recyclerView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());



                if (viewType == 0) { // Sent message layout
                    SentMessageBinding binding = SentMessageBinding.inflate(inflater, parent, false);
                    return new MyRowHolder(binding.getRoot());
                } else { // Received message layout
                    ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(inflater, parent, false);
                    return new MyRowHolder(binding.getRoot());
                }



            }

            public int getItemViewType(int position) {
                List<ChatMessage> currentMessages = theWords.getValue();
                ChatMessage chatMessage = currentMessages.get(position);
                return chatMessage.isSend ? 0 : 1;

            }


            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

                List<ChatMessage> chatMessages = theWords.getValue();

                // Check if chatMessages is null or position is out of bounds
                if (chatMessages == null || position >= chatMessages.size()) {
                    return;
                }


                // Retrieve the ChatMessage object at the specified position
                ChatMessage atThisRow = chatMessages.get(position);
                holder.theTime.setText(atThisRow.timeSent);
                holder.theWord.setText(atThisRow.message);//puts the string in position at theWord TextView


            }


            @Override
            public int getItemCount() {
                //how many rows there are:
                List<ChatMessage> chatMessages = theWords.getValue();
                if (chatMessages == null) {
                    return 0;
                }
                return chatMessages.size();
            }
        });


        //FrameLayout fragmentLocation = findViewById( R.id.frameLayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainModel.selectedMessage.observe(this, (newMessageValue) -> {
            if (newMessageValue != null) {
                MessageDetailsFragment chatFragment = new MessageDetailsFragment(newMessageValue);  //newValue is the newly set ChatMessage
                FragmentManager fMgr = getSupportFragmentManager();
                FragmentTransaction tx = fMgr.beginTransaction();
                tx.replace(R.id.frameLayout, chatFragment);
                tx.addToBackStack("");
                tx.commit();
            }
        });




    }



    protected class MyRowHolder extends RecyclerView.ViewHolder {
        // put variables for what is on a single row:
        TextView theWord;
        TextView theTime;

        //This is a row:
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            //THis holds the message Text:


            List<ChatMessage> message = theWords.getValue();
            itemView.setOnClickListener(clk -> {
                int position = getAbsoluteAdapterPosition();
                ChatMessage selected = message.get(position);
                MainActivityViewModel.selectedMessage.postValue(selected);

            });


            theWord = itemView.findViewById(R.id.message);

            //This holds the time text
            theTime = itemView.findViewById(R.id.time);
        }



    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.item_1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do you want delete the message" )
                    .setTitle("Question:")
                    .setNegativeButton("No", (dialog, cl) -> {
                    })
                    .setPositiveButton("yes", (dialog, cl) -> {
                        theWords=MainModel.messages;
                        List<ChatMessage> message = theWords.getValue();

                        int position = myAdapter.getItemCount() - 1;
                        ChatMessage removedMessage = message.get(position);
                        message.remove(position);
                        myAdapter.notifyItemRemoved(position);
                    }).show();
        } else if (item.getItemId() == R.id.item_2) {
            Toast.makeText(getApplicationContext(), "Version 1.0, made by Wu", Toast.LENGTH_SHORT).show();


        }
        return true;
    }

}


