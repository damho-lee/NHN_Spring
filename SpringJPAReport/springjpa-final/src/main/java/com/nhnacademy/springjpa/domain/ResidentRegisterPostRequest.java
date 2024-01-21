package com.nhnacademy.springjpa.domain;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class ResidentRegisterPostRequest {
    int familySerialNumber;
    String relationShip;
}
