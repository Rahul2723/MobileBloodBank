package com.example.reena.mobilebloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Loginactivity extends AppCompatActivity {

    EditText username, password;
    Button login, register;
    CheckBox rememberme;
    TextView forgetpassword;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginform);
        sharedPreferences = getSharedPreferences("Userinfo", 0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        rememberme = findViewById(R.id.rememberme);
        forgetpassword = findViewById(R.id.forgetpassword);

        if (sharedPreferences.getBoolean("rememberme", false)
                ) {
            Intent  intent=new Intent(Loginactivity.this, Naviation.class);
            startActivity(intent);

            finish();

        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Loginactivity.this,UserListActivity.class));
                String usernamevalue = username.getText().toString();
                String passwordvalue = password.getText().toString();

                String registeredUsername = sharedPreferences.getString("username", "");
                String registeredPassword = sharedPreferences.getString("password", "");
                Log.i("check",usernamevalue+"::"+registeredUsername);
                Log.i("check",passwordvalue+"::"+registeredPassword);

                if (registeredUsername.equals(usernamevalue) && registeredPassword.equals(passwordvalue)) {


                    Toast.makeText(Loginactivity.this, "Login sucess", Toast.LENGTH_SHORT).show();

                    if (rememberme.isChecked()) {
                        sharedPreferences.edit().putBoolean("rememberme", true).apply();
                    }
                    startActivity(new Intent(Loginactivity.this, Naviation.class));
                    finish();
                } else {
                    Toast.makeText(Loginactivity.this, "Login failure", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}
