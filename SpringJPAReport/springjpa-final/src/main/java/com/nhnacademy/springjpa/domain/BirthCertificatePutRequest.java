package com.nhnacademy.springjpa.domain;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class BirthCertificatePutRequest {
    String birthReportQualificationsCode;
    String email;
    String phoneNumber;
}
