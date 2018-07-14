package com.example.reena.mobilebloodbank;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AutocompleteAdapter extends ArrayAdapter<String> {
    Context context;

    public AutocompleteAdapter(Context context, ArrayList<String> list) {
        super(context, 0, list);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(getItem(position));
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(Color.BLACK);
        return textView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(getItem(position));
        textView.setPadding(10, 10, 10, 10);
        textView.setTextColor(Color.BLACK);
        return textView;    }
}
