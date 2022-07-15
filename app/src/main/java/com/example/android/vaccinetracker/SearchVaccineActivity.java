package com.example.android.vaccinetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class SearchVaccineActivity extends AppCompatActivity {

    public static final String EXTRA_PIN = "com.example.android.vaccinetracker.EXTRA_PIN";
    public static final String EXTRA_DATE = "com.example.android.vaccinetracker.EXTRA_DATE";
    final Calendar myCalendar = Calendar.getInstance();
    EditText enterDateEt;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_vaccine);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.abs_layout);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ImageView backButton = findViewById(R.id.backButtonSearchVaccine);
        backButton.setOnClickListener(v -> {
            onBackPressed();
        });

        RelativeLayout button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVaccineActivity();
            }
        });

        enterDateEt = findViewById(R.id.et2);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        enterDateEt.setOnClickListener(v -> {
            new DatePickerDialog(SearchVaccineActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        enterDateEt.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void openVaccineActivity() {
        EditText pinEt = findViewById(R.id.et1);
        String pinCode = pinEt.getText().toString();
        String date = enterDateEt.getText().toString();

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