package com.example.android.vaccinetracker;

public class VaccineData {

    private String mVenue;
    private String mDate;
    private String mDose1;
    private String mDose2;
    private String mMinAgeLimit;
    private String mVaccineFee;
    private String mVaccine;

    public VaccineData() {}
    public VaccineData(String venue, String date, String dose1, String dose2, String minAgeLimit, String vaccine) {
        mVenue = venue;
        mDate = date;
        mDose1 = dose1;
        mDose2 = dose2;
        mMinAgeLimit = minAgeLimit;
//        mVaccineFee = vaccineFee;
        mVaccine = vaccine;
    }

    public String getVenue() {
        return mVenue;
    }

    public void setVenue(String venue) {
        mVenue = venue;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDose1() {
        return mDose1;
    }

    public void setDose1(String dose1) {
        mDose1 = dose1;
    }

    public String getDose2() {
        return mDose2;
    }

    public void setDose2(String dose2) {
        mDose2 = dose2;
    }

    public String getMinAgeLimit() {
        return mMinAgeLimit;
    }

    public void setMinAgeLimit(String minAgeLimit) {
        mMinAgeLimit = minAgeLimit;
    }

//    public String getVaccineFee() {
//        return  mVaccineFee;
//    }
//
//    public void setVaccineFee(String vaccineFee) {
//        mVaccineFee = vaccineFee;
//    }

    public String getVaccine() {
        return mVaccine;
    }

    public void setVaccine(String vaccine) {
        mVaccine = vaccine;
    }
}
