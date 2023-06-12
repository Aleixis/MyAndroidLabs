package com.example.wusandroidlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.wusandroidlabs.databinding.ActivitySecondBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 20;
   // private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 20;
   private static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 21;


    ActivitySecondBinding binding;

    @Override
    protected void onPause() {
        super.onPause(

        );
        SharedPreferences savedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = savedPrefs.edit();
        edit.putString("Telephone", binding.editTextPhone2.getText().toString());
        edit.apply();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        binding.backbutton.setText("Welcome back " + emailAddress);

        binding.dialButton.setOnClickListener(cl -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + binding.editTextPhone2.getText().toString()));
            //startActivity(call);
            if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                startActivity(call);
            } else {
                ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION_REQUEST_CODE);
            }
        });

         ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
         new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {

                    @Override

                    public void onActivityResult(ActivityResult result) {

                        if (result.getResultCode() == Activity.RESULT_OK) {

                            Intent data = result.getData();
                            Bitmap thumpnail = data.getParcelableExtra("data");
                            binding.cameraView.setImageBitmap(thumpnail);

                            FileOutputStream fOut = null;

                            try {
                                fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);

                                thumpnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);

                                fOut.flush();

                                fOut.close();

                            } catch (FileNotFoundException e) {
                                e.printStackTrace();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }

                    }

                });



        binding.changePictureButton.setOnClickListener(tk -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraResult.launch(cameraIntent);

                //startActivity(cameraIntent);
            } else {
                ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
            }


           //cameraResult.launch(cameraIntent);
        });



        String filename = "Picture.png";
        File file = new File(getFilesDir(), filename);

        if (file.exists()) {
            // Load the image file into the bitmap object
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            // Set the bitmap as the image source for the ImageView
            binding.cameraView.setImageBitmap(bitmap);
        }

        SharedPreferences savedPrefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String savedPhoneNumber = savedPrefs.getString("Telephone", "");
        Log.e("telephone", savedPhoneNumber);




            }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
        }
        if (requestCode == CALL_PHONE_PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(Intent.ACTION_DIAL));
        }


    }


}






