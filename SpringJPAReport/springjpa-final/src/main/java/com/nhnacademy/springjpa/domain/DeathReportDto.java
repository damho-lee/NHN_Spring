package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeathReportDto {
    private LocalDateTime deathReportDate;
    private String name;
    private String residentRegistrationNumber;
    private LocalDateTime deathDate;
    private String deathPlaceCode;
    private String deathPlaceAddress;
    private String deathReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
