package com.example.patientapp;

public class Billgen {
    private String med_name;
    private String price;

    public Billgen()
    {

    }
    public Billgen(String med_name, String price) {
        this.med_name = med_name;
        this.price=price;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}

