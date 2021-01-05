package com.example.projectappdoctruyen.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Story implements Serializable {
    private int id;
    private String name;
    private String conTent;

    public Story() {
    }
    public Story(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Story(int id, String name,String conTent) {
        this.id = id;
        this.name = name;
        this.conTent = conTent;
    }

    public String getConTent() {
        return conTent;
    }

    public void setConTent(String conTent) {
        this.conTent = conTent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
