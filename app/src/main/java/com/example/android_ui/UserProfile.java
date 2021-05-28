package com.example.android_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName,Email,Phone,Password;
    Button updateBtn;
    TextView firstText, secondText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullName = findViewById(R.id.full_names);
        Email = findViewById(R.id.email);
        Phone = findViewById(R.id.phone_number);
        Password = findViewById(R.id.password);
        firstText = findViewById(R.id.text1);
        secondText = findViewById(R.id.text2);

        //Show all data
        showAllUserData();
    }

    private void showAllUserData(){

        Intent intent = getIntent();

        String user_username = intent.getStringExtra("username");
        String user_full_names = intent.getStringExtra("full_names");
        String user_email = intent.getStringExtra("email");
        String user_phone = intent.getStringExtra("phone_number");
        String user_password = intent.getStringExtra("password");

        firstText.setText(user_full_names);
        secondText.setText(user_username);
        fullName.getEditText().setText(user_full_names);
        Email.getEditText().setText(user_email);
        Phone.getEditText().setText(user_phone);
        Password.getEditText().setText(user_password);

    }
}