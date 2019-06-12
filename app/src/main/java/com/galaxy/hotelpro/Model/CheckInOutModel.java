package com.galaxy.hotelpro.Model;

public class CheckInOutModel {

    String TranID,CheckIn_DateTime,Head_CheckOut_DateTime,Net_Amount,GuestName,Room_Name;

    public CheckInOutModel(){}

    public CheckInOutModel(String tranID, String checkIn_DateTime, String head_CheckOut_DateTime, String net_Amount, String guestName, String room_Name) {
        TranID = tranID;
        CheckIn_DateTime = checkIn_DateTime;
        Head_CheckOut_DateTime = head_CheckOut_DateTime;
        Net_Amount = net_Amount;
        GuestName = guestName;
        Room_Name = room_Name;
    }

    public String getTranID() {
        return TranID;
    }

    public void setTranID(String tranID) {
        TranID = tranID;
    }

    public String getCheckIn_DateTime() {
        return CheckIn_DateTime;
    }

    public void setCheckIn_DateTime(String checkIn_DateTime) {
        CheckIn_DateTime = checkIn_DateTime;
    }

    public String getHead_CheckOut_DateTime() {
        return Head_CheckOut_DateTime;
    }

    public void setHead_CheckOut_DateTime(String head_CheckOut_DateTime) {
        Head_CheckOut_DateTime = head_CheckOut_DateTime;
    }

    public String getNet_Amount() {
        return Net_Amount;
    }

    public void setNet_Amount(String net_Amount) {
        Net_Amount = net_Amount;
    }

    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getRoom_Name() {
        return Room_Name;
    }

    public void setRoom_Name(String room_Name) {
        Room_Name = room_Name;
    }
}
