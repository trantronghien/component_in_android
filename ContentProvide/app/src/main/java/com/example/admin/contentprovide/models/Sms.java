package com.example.admin.contentprovide.models;

/**
 * Created by admin on 1/20/2017.
 */

public class Sms {
    private String name ;
    private String number;
    private String bodySms;
    private String duration;

    public Sms() {
    }

    public Sms(String name, String number, String bodySms, String duration) {
        this.name = name;
        this.number = number;
        this.bodySms = bodySms;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBodySms() {
        return bodySms;
    }

    public void setBodySms(String bodySms) {
        this.bodySms = bodySms;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
