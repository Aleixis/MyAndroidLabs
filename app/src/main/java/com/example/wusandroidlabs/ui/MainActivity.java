package com.example.wusandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.wusandroidlabs.R;
import com.example.wusandroidlabs.databinding.ActivityMainBinding;

import com.example.wusandroidlabs.data.MainViewModel;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding variableBinding;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());



       variableBinding.picture.setImageResource(R.drawable.logo_algonquin);
        variableBinding.myimagebutton.setImageResource(R.drawable.logo_algonquin);

        variableBinding.button.setOnClickListener(click -> {
                    model.editString.postValue(variableBinding.myedittext.getText().toString());


                });

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("your edit text has" + s);
        });

            model.isSelected.observe(this, isSelected->{
            variableBinding.checkboxCoffee.setChecked(isSelected);
            variableBinding.radioCoffee.setChecked(isSelected);
            variableBinding.switchCoffee.setChecked(isSelected);

        });


        variableBinding.checkboxCoffee.setOnCheckedChangeListener((checkboxCoffee, isChecked) -> {
            model.isSelected.postValue(isChecked);
            Toast.makeText(getApplicationContext(), "The value is now: " + isChecked, Toast.LENGTH_SHORT).show();
        });

        variableBinding.switchCoffee.setOnCheckedChangeListener((switchCoffee, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });

        variableBinding.radioCoffee.setOnCheckedChangeListener((radioCoffee, isChecked) -> {
            model.isSelected.postValue(isChecked);
        });






        variableBinding.myimagebutton.setOnClickListener(click -> {
            Toast.makeText(getApplicationContext(), "The width = " + variableBinding.myimagebutton.getWidth() + " and height = " + variableBinding.myimagebutton.getHeight(), Toast.LENGTH_SHORT).show();

        });



    }
}







