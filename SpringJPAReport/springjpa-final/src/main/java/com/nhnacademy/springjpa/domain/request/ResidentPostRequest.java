package com.nhnacademy.springjpa.domain.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ResidentPostRequest {
    int residentSerialNumber;
    String name;
    String residentRegistrationNumber;
    String gender;
    LocalDateTime birthDate;
    String birthPlaceCode;
    String registrationBaseAddress;
}
