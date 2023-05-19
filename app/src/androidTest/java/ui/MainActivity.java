package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wusandroidlabs.R;
import com.example.wusandroidlabs.databinding.ActivityMainBinding;

import java.text.CollationElementIterator;

import data.MainViewModel;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());
        setContentView(R.layout.activity_main);


        variableBinding.button.setOnClickListener(click -> {
            model.editString.postValue(variableBinding.textview.getText().toString());
            model.editString.observe( this ,s ->{
                variableBinding.textview.setText("your edit text has" +s);
                    });
        }

        );

    }

}










