package com.nhnacademy.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDto {
    private int residentSerialNumber;
    private String name;
    private Integer householdSerialNumber; // household_serial_number
    private String birthDeathReportCode;
    private Long countOfCertificateIssue;
}
