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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

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
}