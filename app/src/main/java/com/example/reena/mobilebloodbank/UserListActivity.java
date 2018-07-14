package com.example.reena.mobilebloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class UserListActivity extends AppCompatActivity {
    LinearLayout container;

    DatabaseHelper databaseHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        container = findViewById(R.id.container);
        databaseHelper = new DatabaseHelper(this);
    }

    public void populateData() {
        ArrayList<Userinfo> List = databaseHelper.getUserList();
        for (int i = 0; i < List.size(); i++) {
            Userinfo info = List.get(i);

        }
        for (final Userinfo info : List) {
            TextView textView = new TextView(this);
            textView.setText(info.username + "\nAddress:" + info.address);
            container.addView(textView);


            View view = LayoutInflater.from(this).inflate(R.layout.item, null);
            TextView username = view.findViewById(R.id.username);
            TextView address = view.findViewById(R.id.address);
            ImageView imageView = view.findViewById(R.id.image);
            if (info.imageView != null)
                imageView.setImageBitmap(Register_activity.getbitmap(info.imageView));
                username.setText(info.username);
            address.setText(info.address);

            container.addView(view);


        }
    }
}
