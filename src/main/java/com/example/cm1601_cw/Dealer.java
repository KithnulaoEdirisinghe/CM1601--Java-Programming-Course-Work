package com.example.cm1601_cw;

public class Dealer {

    private String dealerCode;
    private String dealerName;
    private String dealerPhone;
    private String dealerLocation;

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
    public void setDealerPhone(String dealerPhone) {
        this.dealerPhone = dealerPhone;
    }
    public void setDealerLocation(String dealerLocation) {
        this.dealerLocation = dealerLocation;
    }

    public String getDealerCode() {
        return dealerCode;
    }
    public String getDealerName() {
        return dealerName;
    }
    public String getDealerPhone() {
        return dealerPhone;
    }
    public String getDealerLocation() {
        return dealerLocation;
    }
}
