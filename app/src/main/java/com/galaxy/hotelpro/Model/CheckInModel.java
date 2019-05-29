package com.galaxy.hotelpro.Model;

import java.io.Serializable;

public class CheckInModel implements Serializable {

    String Sr,RoomType,Room,BedType,Charges,Amount,Extra,Balance;

    public CheckInModel(){}

    public CheckInModel(String sr, String roomType,
                                String room, String bedType, String charges,
                                String amount, String extra, String balance) {
        Sr = sr;
        RoomType = roomType;
        Room = room;
        BedType = bedType;
        Charges = charges;
        Amount = amount;
        Extra = extra;
        Balance = balance;
    }

    public String getSr() {
        return Sr;
    }

    public void setSr(String sr) {
        Sr = sr;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getBedType() {
        return BedType;
    }

    public void setBedType(String bedType) {
        BedType = bedType;
    }

    public String getCharges() {
        return Charges;
    }

    public void setCharges(String charges) {
        Charges = charges;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getExtra() {
        return Extra;
    }

    public void setExtra(String extra) {
        Extra = extra;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }
}
