package com.example.android.vaccinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class VaccineActivity extends AppCompatActivity {

//    private EditText pinEt, dateEt;
    private TextView venueTv, dateTv, dose1Tv, dose2Tv, minAgeLimitTv, vaccineTv;

    List<VaccineData> vaccineDatas;
    RecyclerView recyclerView;
    VaccineDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        Intent intent = getIntent();
        String pinCode = intent.getStringExtra(MainActivity.EXTRA_PIN);
        String date = intent.getStringExtra(MainActivity.EXTRA_DATE);

        recyclerView = findViewById(R.id.data_recyclerview);

//        pinEt = findViewById(R.id.et1);
//        dateEt = findViewById(R.id.et2);

        venueTv = findViewById(R.id.venue_tv);
        dateTv = findViewById(R.id.date_tv);
        dose1Tv = findViewById(R.id.dose1_tv);
        dose2Tv = findViewById(R.id.dose2_tv);
        minAgeLimitTv = findViewById(R.id.minAgeLimit_tv);
        vaccineTv = findViewById(R.id.vaccineName_tv);

        vaccineDatas = new ArrayList<>();

        showVaccineData(pinCode, date);

    }

    private void showVaccineData(String pinCode, String dateInput) {
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pinCode + "&date=" + dateInput;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int flag = 0;
                    JSONArray main_array = response.getJSONArray("centers");
                    for(int i=0; i<main_array.length(); i++){
                        JSONObject each = main_array.getJSONObject(i);
                        JSONArray sessions = each.getJSONArray("sessions");
                        VaccineData vaccineData = new VaccineData();
                        for(int j=0; j<sessions.length(); j++){
                            JSONObject session = sessions.getJSONObject(j);

                            /* Venue Name */
                            String venueName = each.getString("name");
                            String venueAdress = each.getString("address");
                            String stateName = each.getString("state_name");
                            String districtName = each.getString("district_name");
                            if(!each.getString("block_name").equals("Not Applicable")) {
                                String blockName = each.getString("block_name");
                                vaccineData.setVenue(venueName + ", " + venueAdress + ", " + stateName + ", " + districtName
                                        + ", " + blockName + ", " + pinCode);
                            } else {
                                vaccineData.setVenue(venueName + ", " + venueAdress + ", " + stateName + ", " + districtName
                                        + ", " + pinCode);
                            }

                            /* Date */
                            String date = session.getString("date");
                            vaccineData.setDate("Date: " + date);

                            /* Dose 1 and Dose 2 */
                            int available_dose1 = session.getInt("available_capacity_dose1");
                            int available_dose2 = session.getInt("available_capacity_dose2");
                            String available_dose1_str = String.valueOf(available_dose1);
                            String available_dose2_str =String.valueOf(available_dose2);
                            vaccineData.setDose1("Availability of dose-1: " + available_dose1_str);
                            vaccineData.setDose2("Availability of dose-2: " + available_dose2_str);

                            /* Minimum age limit */
                            int minAgeLimit = session.getInt("min_age_limit");
                            String minAgeLimit_str = String.valueOf(minAgeLimit);
                            vaccineData.setMinAgeLimit("Min age limit: " + minAgeLimit_str);

                            /* Vaccine Name */
                            String vaccine = session.getString("vaccine");
                            vaccineData.setVaccine("Vaccine: " + vaccine);

                            vaccineDatas.add(vaccineData);
                            flag = 1;

                        }
                    }
                    if(flag!=1) {
                        Toast.makeText(VaccineActivity.this, "No vaccines found!", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new VaccineDataAdapter(vaccineDatas,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VaccineActivity.this, "Enter correct data", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}