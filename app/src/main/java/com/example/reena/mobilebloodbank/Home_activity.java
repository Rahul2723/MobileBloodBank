package com.example.reena.mobilebloodbank;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;


public class Home_activity extends AppCompatActivity {
   // ImageView popupIcon;
    AutoCompleteTextView autoCompleteTextView;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviation);
       // popupIcon = findViewById(R.id.popUp);
        autoCompleteTextView = findViewById(R.id.bloodgroup);
        databaseHelper = new DatabaseHelper(this);
        Log.i("Size:", "Size" + databaseHelper.getBloodgroup());
        autoCompleteTextView.setAdapter(new AutocompleteAdapter(this, databaseHelper.getBloodgroup()));


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.menu1:
                Intent intent = new Intent(this, Register_activity.class);
                startActivity(intent);
                break;
            case R.id.menu2:
                Toast.makeText(this, "menu2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu3:
                Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
                break;

            case R.id.submenu1:
                Toast.makeText(this, "submenu1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.submenu2:
                Toast.makeText(this, "submenu2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("Userinfo", 0);
                sharedPreferences.edit().putBoolean("rememberme", false).apply();
                startActivity(new Intent(this, Loginactivity.class));
                finish();


        }

        return super.onOptionsItemSelected(item);
    }
}

