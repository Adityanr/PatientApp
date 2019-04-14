package com.example.patientapp;

public class Patientmeds {
    private String med_name;
    private String dose;
    private String doc;
    private String date;
    private int nod;

    public Patientmeds() {
    }

    public Patientmeds(String med_name, String dose,String doc,String date,int nod) {
        this.med_name = med_name;
        this.dose = dose;
        this.doc=doc;
        this.date=date;
        this.nod=nod;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNod() {
        return nod;
    }

    public void setNod(int nod) {
        this.nod = nod;
    }
}

