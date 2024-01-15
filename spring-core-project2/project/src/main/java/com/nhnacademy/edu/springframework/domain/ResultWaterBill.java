package com.nhnacademy.edu.springframework.domain;

public class ResultWaterBill {
    private final String city;
    private final String sector;
    private final int unitPrice;
    private final int billTotal;

    public ResultWaterBill(String city, String sector, int unitPrice, int billTotal) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getBillTotal() {
        return billTotal;
    }
}
