package com.example.android.vaccinetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
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

    private TextView venueTv, dateTv, dose1Tv, dose2Tv, minAgeLimitTv, vaccinationFeeTv, vaccineTv;

    List<VaccineData> vaccineDatas;
    RecyclerView recyclerView;
    VaccineDataAdapter adapter;

    ProgressBar mProgressBar;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.abs_layout);

        mProgressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        String pinCode = intent.getStringExtra(MainActivity.EXTRA_PIN);
        String date = intent.getStringExtra(MainActivity.EXTRA_DATE);
        vaccinationFeeTv = findViewById(R.id.vaccinationFee_tv);

        recyclerView = findViewById(R.id.data_recyclerview);

        vaccineDatas = new ArrayList<>();

        mProgressBar.setVisibility(View.VISIBLE);

        showVaccineData(pinCode, date);

    }

    private void showVaccineData(String pinCode, String dateInput) {
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pinCode + "&date=" + dateInput;

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mProgressBar.setVisibility(View.INVISIBLE);
                    int flag = 0;
                    JSONArray main_array = response.getJSONArray("centers");
                    for(int i=0; i<main_array.length(); i++){
                        JSONObject each = main_array.getJSONObject(i);
                        JSONArray sessions = each.getJSONArray("sessions");
                        for(int j=0; j<sessions.length(); j++) {
                            VaccineData vaccineData = new VaccineData();
                            JSONObject session = sessions.getJSONObject(j);

                            /* Venue Name */
                            String venueName = each.getString("name");
                            vaccineData.setVenue(venueName);

                            /* Venue Address */
                            String venueAdress = each.getString("address");
                            vaccineData.setVenueAddress(venueAdress);

                            String stateName = each.getString("state_name");
                            String districtName = each.getString("district_name");
                            vaccineData.setStateDist(stateName + ", " + districtName + ", " + pinCode);

                            /* Date */
                            String date = session.getString("date");
                            vaccineData.setDate("Date: " + date);

                            /* Dose 1 and Dose 2 */
                            int available_dose1 = session.getInt("available_capacity_dose1");
                            int available_dose2 = session.getInt("available_capacity_dose2");
                            String available_dose1_str = String.valueOf(available_dose1);
                            String available_dose2_str =String.valueOf(available_dose2);
                            vaccineData.setDose1("1st Dose: " + available_dose1_str);
                            vaccineData.setDose2("2nd Dose: " + available_dose2_str);

                            /* Minimum age limit */
                            int minAgeLimit = session.getInt("min_age_limit");
                            String minAgeLimit_str = String.valueOf(minAgeLimit);
                            vaccineData.setMinAgeLimit(minAgeLimit_str + "+");

                            /* vaccine fee */
                            String fee_type = each.getString("fee_type");
                            vaccineData.setVaccineFee(fee_type);
                            if(!fee_type.equals("Free")) {
                                JSONArray vaccine_fees = each.getJSONArray("vaccine_fees");
                                JSONObject vaccine_object = vaccine_fees.getJSONObject(0);
                                String fee = vaccine_object.getString("fee");
                                vaccineData.setVaccineFee2(": ₹" + fee);
                            } else {
                                vaccineData.setVaccineFee2(": ₹0");
                            }

                            /* Vaccine Name */
                            String vaccine = session.getString("vaccine");
                            vaccineData.setVaccine(vaccine);

                            vaccineDatas.add(vaccineData);
                            flag = 1;

                        }
                    }
                    if(flag!=1) {
                        Toast.makeText(VaccineActivity.this, "No vaccines available!", Toast.LENGTH_LONG).show();
                        onBackPressed();
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