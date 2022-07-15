package com.example.android.vaccinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        CardView bookVaccinationButton = findViewById(R.id.bookVaccinationButton);
        CardView searchVaccinesButton = findViewById(R.id.searchVaccinesButton);

        bookVaccinationButton.setOnClickListener(v -> {
            String cowin_url = "https://www.cowin.gov.in/home";
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(cowin_url)));
        });

        searchVaccinesButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SearchVaccineActivity.class));
        });
    }
}