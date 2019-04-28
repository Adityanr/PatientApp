package com.example.patientapp;

public class Labinit {

    private String Type;
    private String Result;
    private String Date;

    public Labinit() {
    }

    public Labinit(String Type, String Result,String Date) {
        this.Type = Type;
        this.Result = Result;
        this.Date=Date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public String getDate() {
            return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

}
