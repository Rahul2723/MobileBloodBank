package com.example.reena.mobilebloodbank;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;

public class Naviation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AutoCompleteTextView autoCompleteTextView, autoCompleteTextView1;
    DatabaseHelper databaseHelper;
    Button search;
    public static final String TAG = "BloodGroup";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        search = findViewById(R.id.search);

        autoCompleteTextView = findViewById(R.id.bloodgroup);
        databaseHelper = new DatabaseHelper(this);
        Log.i("Size:", "Size" + databaseHelper.getBloodgroup());
        autoCompleteTextView.setAdapter(new AutocompleteAdapter(this, databaseHelper.getBloodgroup()));

        autoCompleteTextView1 = findViewById(R.id.city);
        databaseHelper = new DatabaseHelper(this);
        Log.i("Size:", "Size" + databaseHelper.getCity());
        autoCompleteTextView1.setAdapter(new AutocompleteAdapter(this, databaseHelper.getCity()));

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Blood Group" + autoCompleteTextView.getText().toString());
                Log.d(TAG, "City" + autoCompleteTextView1.getText().toString());
                String bldGp = autoCompleteTextView.getText().toString();
                String cty = autoCompleteTextView1.getText().toString();

                ArrayList<Matchuser> matchuser = databaseHelper.search(bldGp, cty);


                Intent intent = new Intent(Naviation.this, MapsActivity.class);

                /*intent.putExtra("lat",matchuser.);
                intent.putExtra("lang",26);*/
                if (matchuser.size() > 0) {
                    intent.putExtra("name", matchuser.get(0).name);
                    intent.putExtra("address", matchuser.get(0).address);
                }
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.search) {
        } else if (id == R.id.update) {
            startActivity(new Intent(this, Update.class));

        } else if (id == R.id.aboutus) {
            startActivity(new Intent(this, Aboutus.class));

        } else if (id == R.id.logout) {
            startActivity(new Intent(this, first_page.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
