package com.example.reena.mobilebloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class first_page extends AppCompatActivity {
    Button login, register;

android.support.v7.widget.Toolbar toolbarHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstlayoutfile);
        toolbarHome=findViewById(R.id.toolbar);
       setSupportActionBar(toolbarHome);

        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(first_page.this, Register_activity.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(first_page.this, Loginactivity.class);
                startActivity(intent);
            }
        });
    }
}