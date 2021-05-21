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

    private Boolean validateName(){

        String val = regName.getEditText().toString();

        if(val.isEmpty()){

            regName.setError("Field cannot be empty");
            return false;
        }else{

            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername(){

        String val = regUsername.getEditText().toString();
        String noWhiteSpace = "\\\\A\\\\w{4,20}\\\\z";

        if(val.isEmpty()){

            regUsername.setError("Field cannot be empty");
            return false;
        }else if(val.length() > 15 ){

            regUsername.setError("Username is too long");
            return false;
        }else if(!val.matches(noWhiteSpace)){

            regUsername.setError("White spaces are not allowed");
            return false;
        }
        else{

            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail(){

        String val = regEmail.getEditText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+";

        if(val.isEmpty()){

            regEmail.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(emailPattern)){

            regEmail.setError("Invalid Email Address");
            return false;
        }else{

            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhone(){

        String val = regPhone.getEditText().toString();

        if(val.isEmpty()){

            regPhone.setError("Field cannot be empty");
            return false;
        }else{

            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){

        String val = regPassword.getEditText().toString();
        String passwordPattern = "^" +
                                //"(?=.*[0-9])" +         //at least 1 digit
                                //"(?=.*[a-z])" +         //at least 1 lower case letter
                                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                                "(?=.*[a-zA-Z])" +      //any letter
                                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                                "(?=\\S+$)" +           //no white spaces
                                ".{4,}" +               //at least 4 characters
                                "$";

        if(val.isEmpty()){

            regPassword.setError("Field cannot be empty");
            return false;
        }else if(!val.matches(passwordPattern)){

            regPassword.setError("Password is too weak");
            return false;
        }else{

            regPassword.setError(null);
            return true;
        }
    }


    @Override
    public void onClick(View v) {

        if(!validateName() | !validateUsername() | validateEmail() | validatePhone() | validatePassword()){
            return;
        }

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