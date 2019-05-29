package com.galaxy.hotelpro.Model;

public class CheckInGuestInfoModel {

    String name,checkInDate,noOfNights,checkOutDate,
            payType,currency,exChangeRate;

    public CheckInGuestInfoModel(String name, String checkInDate, String noOfNights, String checkOutDate, String payType, String currency, String exChangeRate) {
        this.name = name;
        this.checkInDate = checkInDate;
        this.noOfNights = noOfNights;
        this.checkOutDate = checkOutDate;
        this.payType = payType;
        this.currency = currency;
        this.exChangeRate = exChangeRate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getNoOfNights() {
        return noOfNights;
    }

    public void setNoOfNights(String noOfNights) {
        this.noOfNights = noOfNights;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExChangeRate() {
        return exChangeRate;
    }

    public void setExChangeRate(String exChangeRate) {
        this.exChangeRate = exChangeRate;
    }
}
