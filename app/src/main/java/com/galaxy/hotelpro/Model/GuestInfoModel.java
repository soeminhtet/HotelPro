package com.galaxy.hotelpro.Model;

public class GuestInfoModel {

    String name,dispercent;

    public GuestInfoModel() {
    }

    public GuestInfoModel(String name, String dispercent) {
        this.name = name;
        this.dispercent = dispercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDispercent() {
        return dispercent;
    }

    public void setDispercent(String dispercent) {
        this.dispercent = dispercent;
    }
}
