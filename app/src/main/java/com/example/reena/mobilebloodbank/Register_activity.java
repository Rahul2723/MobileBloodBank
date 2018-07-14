package com.example.reena.mobilebloodbank;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;


public class Register_activity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText username, password, email, phone, address, age, bloodgroup;
    RadioGroup group;
    ImageView image11;
    Button register, cancel;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("Userinfo", 0);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);
        group = findViewById(R.id.group);

        bloodgroup = findViewById(R.id.bloodgroup);
        image11 = findViewById(R.id.image1);
        register = findViewById(R.id.register);
        cancel = findViewById(R.id.cancel);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernamevalue = username.getText().toString();
                String passwordvalue = password.getText().toString();
                String emailvalue = email.getText().toString();
                String phonevalue = phone.getText().toString();
                String agevalue = age.getText().toString();
                int mainage=Integer.parseInt(agevalue);
                String bloodgroupvalue = bloodgroup.getText().toString();

                RadioButton checkbtn = findViewById(group.getCheckedRadioButtonId());
                String gendervalue = checkbtn.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", usernamevalue);
                editor.putString("password", passwordvalue);
                editor.putString("email", emailvalue);
                editor.putString("phone", phonevalue);
                editor.putString("gender", gendervalue);
                editor.putString("age", agevalue);
                editor.putString("bloodgroup", bloodgroupvalue);
                editor.apply();




                ContentValues contentValues = new ContentValues();
                contentValues.put("username", usernamevalue);
                contentValues.put("password", passwordvalue);
                contentValues.put("email", emailvalue);
                contentValues.put("phone", phonevalue);
                contentValues.put("gender", gendervalue);
                contentValues.put("bloodgroup", bloodgroupvalue);
                if (bitmap != null)
                    contentValues.put("image", getBlob(bitmap));

                if (mainage<=17&& mainage<=66)
                    contentValues.put("age", agevalue);
                databaseHelper.insertUser(contentValues);
                Toast.makeText(Register_activity.this, "User Registered!!!", Toast.LENGTH_SHORT).show();

            }
        });

        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("HELLO", "HHEELLL");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 101);
            }
        });

    }

    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            if (requestCode == 101) {
                bitmap = (Bitmap) data.getExtras().get("data");
                image11.setImageBitmap(bitmap);
            }
    }

    public static byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        return bArray;
    }

    public static Bitmap getbitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
}



