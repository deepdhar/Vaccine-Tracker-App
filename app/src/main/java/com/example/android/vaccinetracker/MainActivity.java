package com.example.android.vaccinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PIN = "com.example.android.vaccinetracker.EXTRA_PIN";
    public static final String EXTRA_DATE = "com.example.android.vaccinetracker.EXTRA_DATE";

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        RelativeLayout button = findViewById(R.id.button);
        RelativeLayout bookVaccineButton = findViewById(R.id.bookVaccineButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVaccineActivity();
            }
        });

        bookVaccineButton.setOnClickListener(view -> {
            String cowin_url = "https://www.cowin.gov.in/home";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(cowin_url)));
        });

    }

    private void openVaccineActivity() {
        EditText pinEt = findViewById(R.id.et1);
        EditText dateEt = findViewById(R.id.et2);
        String pinCode = pinEt.getText().toString();
        String date = dateEt.getText().toString();

        if(!pinCode.equals("") && !date.equals("")) {
            Intent i = new Intent(this, VaccineActivity.class);
            i.putExtra(EXTRA_PIN, pinCode);
            i.putExtra(EXTRA_DATE, date);
            startActivity(i);
        } else {
            Toast.makeText(this, "Fill both fields", Toast.LENGTH_SHORT).show();
        }
    }

}