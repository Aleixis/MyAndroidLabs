
        package com.example.wusandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.wusandroidlabs.databinding.ActivityMainBinding;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    protected ActivityMainBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        Log.w( TAG,"In onCreate() - Loading Widgets" );
        setContentView(binding.getRoot());

       // File myDir = getFilesDir();
        //String path = myDir.getAbsolutePath();



        binding.loginButton.setOnClickListener((v) -> {
            Log.e( TAG,"You clicked the button" );

            SharedPreferences savedPrefs = getSharedPreferences("MyData",Context.MODE_PRIVATE);

            SharedPreferences.Editor edit = savedPrefs.edit();
            edit.putString("LoginName", binding.emailEditText.getText().toString());
            edit.putFloat("Hi",4.5f);
            edit.putInt("Age",21);
            edit.apply();

            savedPrefs.getFloat("Hi", 0);
            savedPrefs.getInt("Age", 0);


            Intent nextPage = new Intent(this,SecondActivity.class);
            nextPage.putExtra( "EmailAddress", binding.emailEditText.getText().toString() );
            startActivity(nextPage);

        });



    }


    @Override
    protected void onStart() {

        super.onStart();
        Log.w( TAG,"In onStart() - Loading Widgets" );
    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.w( TAG,"In onResume() - Loading Widgets" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w( TAG,"In onPause() - Loading Widgets" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w( TAG,"In onStop() - Loading Widgets" );
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w( TAG,"In onDestroy() - Loading Widgets" );
    }







}