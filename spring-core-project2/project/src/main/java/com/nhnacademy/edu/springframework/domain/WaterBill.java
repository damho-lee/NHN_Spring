package com.nhnacademy.edu.springframework.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WaterBill {
    private final int seq;
    private final String city;
    private final String sector;
    private final int level;
    private final int startSection;
    private final int endSection;
    private final int unitPrice;
    private final int basicPrice;

    @JsonCreator
    public WaterBill(@JsonProperty("순번") int seq,
                     @JsonProperty("지자체명") String city,
                     @JsonProperty("업종") String sector,
                     @JsonProperty("단계") int level,
                     @JsonProperty("구간시작(세제곱미터)") int startSection,
                     @JsonProperty("구간끝(세제곱미터)") int endSection,
                     @JsonProperty("구간금액(원)") int unitPrice,
                     @JsonProperty("단계별 기본요금(원)") int basicPrice) {
        this.seq = seq;
        this.city = city;
        this.sector = sector;
        this.level = level;
        this.startSection = startSection;
        this.endSection = endSection;
        this.unitPrice = unitPrice;
        this.basicPrice = basicPrice;
    }

    public int getSeq() {
        return seq;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getLevel() {
        return level;
    }

    public int getStartSection() {
        return startSection;
    }

    public int getEndSection() {
        return endSection;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getBasicPrice() {
        return basicPrice;
    }
}
