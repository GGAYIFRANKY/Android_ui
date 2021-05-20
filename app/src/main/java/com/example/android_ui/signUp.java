package com.example.android_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity implements View.OnClickListener{

    TextInputLayout regName, regUsername, regEmail, regPhone,regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        regName = findViewById(R.id.full_names);
        regUsername = findViewById(R.id.username);
        regEmail = findViewById(R.id.email);
        regPhone = findViewById(R.id.phone_number);
        regPassword = findViewById(R.id.password);
        regBtn = findViewById(R.id.sign_up);
        regToLoginBtn = findViewById(R.id.sign_in);

        regBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        //Get all the values from the text fields
        String full_names = regName.getEditText().toString();
        String username = regUsername.getEditText().toString();
        String email = regEmail.getEditText().toString();
        String phone_number = regPhone.getEditText().toString();
        String password = regPassword.getEditText().toString();

        UserHelperClass helperClass = new UserHelperClass(full_names, username, email, phone_number, password);

        reference.child(phone_number).setValue(helperClass);
    }
}