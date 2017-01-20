package com.example.admin.contentprovide.models;

import java.io.Serializable;

/**
 * Created by admin on 1/20/2017.
 */

public class Contacts implements Serializable {
    private String name;
    private String phone;

    public Contacts() {
    }

    public Contacts(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.phone;
    }
}
