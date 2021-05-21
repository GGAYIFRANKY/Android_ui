package com.example.android_ui;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button callSignUp, loginBtn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.sign_up);
        image = findViewById(R.id.logo_img);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.sign_in);

        callSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(LoginActivity.this,signUp.class);

        Pair[] pairs = new Pair[7];

        pairs[0] = new Pair<View, String>(image,"logo_image");
        pairs[1] = new Pair<View, String>(logoText,"logo_text");
        pairs[2] = new Pair<View, String>(sloganText,"slogan_text");
        pairs[3] = new Pair<View, String>(username,"username_trans");
        pairs[4] = new Pair<View, String>(password,"password_trans");
        pairs[5] = new Pair<View, String>(loginBtn,"login_trans");
        pairs[6] = new Pair<View, String>(callSignUp,"register_trans");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent,options.toBundle());
        }

    }

    private Boolean validateUsername(){
        String val = username.getEditText().toString();


        if(val.isEmpty()){

            username.setError("Field cannot be empty");
            return false;
        }
        else{

            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = password.getEditText().toString();


        if(val.isEmpty()){

            password.setError("Field cannot be empty");
            return false;
        }else{

            password.setError(null);
            return true;
        }
    }

    public void loginUser(View view){
        if(!validatePassword() | !validateUsername()){
            return;
        }else{

            isUser();
        }

    }

    public void isUser(){

        String userEnteredUsername = username.getEditText().toString().trim();
        String userEnteredPassword = password.getEditText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String fullNamesFromDB = dataSnapshot.child(userEnteredUsername).child("full_names").getValue(String.class);
                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(userEnteredUsername).child("phone_number").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);

                        intent.putExtra("full_names",fullNamesFromDB);
                        intent.putExtra("username",usernameFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("phone_number",phoneFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);
                    }else{
                        password.setError("Wrong Snapshot");
                        password.requestFocus();
                    }
                }else{

                    username.setError("No such user exists");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}