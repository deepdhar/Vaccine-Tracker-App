package com.example.android.vaccinetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VaccineDataAdapter extends RecyclerView.Adapter<VaccineDataAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<VaccineData> vaccineData;
//    VaccineData[] vaccineData;
//    Context context;

    public VaccineDataAdapter(List<VaccineData> vaccineData, Context context) {
        this.vaccineData = vaccineData;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public VaccineDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.data_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VaccineDataAdapter.ViewHolder holder, int position) {
        holder.venueTv.setText(vaccineData.get(position).getVenue());
        holder.dateTv.setText(vaccineData.get(position).getDate());
        holder.dose1Tv.setText(vaccineData.get(position).getDose1());
        holder.dose2Tv.setText(vaccineData.get(position).getDose2());
        holder.minAgeLimitTv.setText(vaccineData.get(position).getMinAgeLimit());
        holder.vaccineTv.setText(vaccineData.get(position).getVaccine());
    }

    @Override
    public int getItemCount() {
        return vaccineData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView venueTv;
        TextView dateTv;
        TextView dose1Tv;
        TextView dose2Tv;
        TextView minAgeLimitTv;
        TextView vaccineTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            venueTv = itemView.findViewById(R.id.venue_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
            dose1Tv = itemView.findViewById(R.id.dose1_tv);
            dose2Tv = itemView.findViewById(R.id.dose2_tv);
            minAgeLimitTv = itemView.findViewById(R.id.minAgeLimit_tv);
            vaccineTv = itemView.findViewById(R.id.vaccineName_tv);
        }
    }
}
