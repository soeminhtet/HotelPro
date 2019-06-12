package com.galaxy.hotelpro.Model;

public class RoomChargesModel {

    String id,kyat,dollar;

    public RoomChargesModel() {
    }

    public RoomChargesModel(String id, String kyat, String dollar) {
        this.id = id;
        this.kyat = kyat;
        this.dollar = dollar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKyat() {
        return kyat;
    }

    public void setKyat(String kyat) {
        this.kyat = kyat;
    }

    public String getDollar() {
        return dollar;
    }

    public void setDollar(String dollar) {
        this.dollar = dollar;
    }
}
