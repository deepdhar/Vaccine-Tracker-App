package com.example.android.vaccinetracker;

public class VaccineData {

    private String mVenue;
    private String mVenueAddress;
    private String mVenueStateDist;
    private String mDate;
    private String mDose1;
    private String mDose2;
    private String mMinAgeLimit;
    private String mVaccineFee;
    private String mVaccineFee2;
    private String mVaccine;

    public VaccineData() {}
    public VaccineData(String venue, String venueAddress, String venueStateDist, String date, String dose1, String dose2,
                       String minAgeLimit, String vaccineFee, String vaccineFee2, String vaccine) {
        mVenue = venue;
        mVenueAddress = venueAddress;
        mVenueStateDist = venueStateDist;
        mDate = date;
        mDose1 = dose1;
        mDose2 = dose2;
        mMinAgeLimit = minAgeLimit;
        mVaccineFee = vaccineFee;
        mVaccineFee2 = vaccineFee2;
        mVaccine = vaccine;
    }

    public String getVenue() {
        return mVenue;
    }

    public void setVenue(String venue) {
        mVenue = venue;
    }

    public String getVenueAddress() {
        return mVenueAddress;
    }

    public void setVenueAddress(String venueAddress) {
        mVenueAddress = venueAddress;
    }

    public String getStateDist() {
        return mVenueStateDist;
    }

    public void setStateDist(String venueStateDist) {
        mVenueStateDist = venueStateDist;
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

    public String getVaccineFee() {
        return  mVaccineFee;
    }

    public void setVaccineFee(String vaccineFee) {
        mVaccineFee = vaccineFee;
    }

    public String getVaccineFee2() {
        return  mVaccineFee2;
    }

    public void setVaccineFee2(String vaccineFee2) {
        mVaccineFee2 = vaccineFee2;
    }

    public String getVaccine() {
        return mVaccine;
    }

    public void setVaccine(String vaccine) {
        mVaccine = vaccine;
    }
}
