package com.example.reena.mobilebloodbank;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "Mobile Blood Bank";
    static int version = 1;

    String SQLCreateTable = "CREATE TABLE if not exists `user1` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`username`\tTEXT,\n" +
            "\t`password`\tTEXT,\n" +
            "\t`email`\tTEXT,\n" +
            "\t`address`\tTEXT,\n" +
            "\t`phone`\tINTEGER,\n" +
            "\t`age`\tINTEGER,\n" +
            "\t`bloodgroup`\tTEXT,\n" +
            "\t`gender`\tTEXT,\n" +
            "\t`image`\tBLOB\n" +
            ")";

    public ArrayList<Matchuser> search(String bloodgroup, String city) {
//    String searching="Select * from user where bloodgroup like "+bloodgroup+"and city like "+city;
        String searching = "Select * from user where bloodgroup like '%'" + bloodgroup + "'%' and address like '%'" + city + "'%'";
        Cursor c = getWritableDatabase().rawQuery(searching, null);
        ArrayList<Matchuser> list = new ArrayList<>();
        while (c.moveToNext()) {
            Matchuser user = new Matchuser();
            user.name = c.getString(c.getColumnIndex("username"));
    /*user.lat=c.getString(c.getColumnIndex("latitude"));
    user.lang=c.getString(c.getColumnIndex("longitude"));*/
            user.address = c.getString(c.getColumnIndex("address"));
            list.add(user);
            Log.d("check", "" + user.name.toString());
        }
        c.close();

        return list;

    }

    public DatabaseHelper(Context context) {

        super(context, name, null, version);
        getWritableDatabase().execSQL(SQLCreateTable);

    }

    public ArrayList<Userinfo> getUserList() {
        String sql = "Select * from user";
        Cursor c = getWritableDatabase().rawQuery(sql, null);
        ArrayList<Userinfo> List = new ArrayList<>();
        while (c.moveToNext()) {
            Userinfo info = new Userinfo();

            info.id = c.getString(c.getColumnIndex("id"));
            info.username = c.getString(c.getColumnIndex("username"));
            info.password = c.getString(c.getColumnIndex("password"));
            info.email = c.getString(c.getColumnIndex("email"));
            info.address = c.getString(c.getColumnIndex("address"));
            info.phone = c.getString(c.getColumnIndex("phone"));
            info.age = c.getString(c.getColumnIndex("age"));
            info.bloodgroup = c.getString(c.getColumnIndex("bloodgroup"));
            info.gender = c.getString(c.getColumnIndex("gender"));
            info.imageView = c.getBlob(c.getColumnIndex("imageView"));
            List.add(info);

        }
        c.close();
        return List;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user1", "", contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<String> getBloodgroup() {

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("A+ve");
        stringArrayList.add("A-ve");
        stringArrayList.add("B+ve");
        stringArrayList.add("B-ve");
        stringArrayList.add("AB+ve");
        stringArrayList.add("AB-ve");
        stringArrayList.add("O+ve");
        stringArrayList.add("O-ve");

        return stringArrayList;

    }

    public ArrayList<String> getCity() {

        ArrayList<String> stringArrayList = new ArrayList<>();
        stringArrayList.add("satobato");
        stringArrayList.add("godawari");
        stringArrayList.add("jawalekhel");
        stringArrayList.add("lagenkhel");
        stringArrayList.add("kumaripati");
        stringArrayList.add("bhaisepati");
        stringArrayList.add("balkhu");
        stringArrayList.add("pulchowk");
        stringArrayList.add("kupondol");
        stringArrayList.add("jsamsikhel");
        stringArrayList.add("gwarko");
        stringArrayList.add("dhapakhel");
        stringArrayList.add("imadol");
        stringArrayList.add("sanepa");
        stringArrayList.add("dhobighat");
        stringArrayList.add("chakupat");


        return stringArrayList;

    }
}
