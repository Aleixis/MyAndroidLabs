package com.example.wusandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The main activity of the application.
 * @author Wu Xinyu
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This holds the text at the centre of the screen
     */
    private TextView tv =null;
    /**
     * This holds the edit text of the screen
     */
    private EditText et =null;
    /**
     * This holds the button of the screen
     */
    private Button btn = null;

    /**
     * Called when the activity is starting.
     *
     * @param savedInstanceState the saved instance state bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editTextText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(clk -> {
            String password = et.getText().toString();
            checkPasswordComplexity(password);
            if (foundUpperCase&&foundNumber&&foundLowerCase&&foundSpecial==true){
            tv.setText("Your password meets the requirements");
            }
            else{
                tv.setText("You shall not pass!");
            }

        });


    }


    /**
     *  This set as false at first
     */
    boolean foundUpperCase = false;
    /**
     *  This set as false at first
      */
    boolean foundLowerCase = false;
    /**
     *  This set as false at first
     */
    boolean foundNumber = false;
    /**
     *  This set as false at first
     */
    boolean foundSpecial = false;
    /**
     * Tests the input password and checks if it aligns with the standard.
     *
     * @param password the String object representing the password to be checked
     * @return true if the password meets the requirements, false otherwise
     */
    boolean checkPasswordComplexity(String password) {

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }
        if (!foundUpperCase) {
            Toast.makeText(this, "You are missing the upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "You are missing the lower case letter", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!foundNumber) {
            Toast.makeText(this, "You are missing the number", Toast.LENGTH_SHORT).show();
            return false;

        } else if (!foundSpecial) {
            Toast.makeText(this, "You are missing the special sign", Toast.LENGTH_SHORT).show();
            return false;

        } else
            return true;
    }

    /**
     * This function is to define the special characters
     * @param c the character to be checked
     * @return false if the character c is not one of the following characters, true if it is
     */
    boolean isSpecialCharacter(char c) {//return true if c is one of: #$%^&*!@?
        switch (c)
        {
            case '#':
            case'$':
            case '%':
            case'^':
            case'&':
            case '?':
            case '!':
            case'@':
            case'*':
                return true;
            default:
                return false;
        }

        //return false otherwise

    }
}