package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BirthReportDto {
    private LocalDateTime birthReportDate;
    private String name;
    private String gender;
    private LocalDateTime birthDate;
    private String birthPlaceCode;
    private String registrationBaseAddress;
    private String birthReportQualificationsCode;
    private String emailAddress;
    private String phoneNumber;
}
