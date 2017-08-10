package com.grishalive.manlist;

import android.support.annotation.NonNull;

public class Model implements Comparable<Model>{

    private String name;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    public Model(String name, String date, String phone) {
        this.name = name;
        this.date = date;
        this.phone = phone;
    }

    @Override
    public int compareTo(@NonNull Model model) {
        return name.compareTo(model.getName());
    }
}
