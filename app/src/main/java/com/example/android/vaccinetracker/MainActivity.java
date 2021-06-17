package com.example.android.vaccinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_PIN = "com.example.android.vaccinetracker.EXTRA_PIN";
    public static final String EXTRA_DATE = "com.example.android.vaccinetracker.EXTRA_DATE";

    private Button button, bookVaccineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        bookVaccineButton = findViewById(R.id.bookVaccineButton);

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

        Intent i = new Intent(this, VaccineActivity.class);
        i.putExtra(EXTRA_PIN, pinCode);
        i.putExtra(EXTRA_DATE, date);
        startActivity(i);
    }

}