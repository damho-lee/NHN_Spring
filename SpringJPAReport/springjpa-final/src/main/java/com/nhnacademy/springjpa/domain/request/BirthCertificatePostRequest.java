package com.nhnacademy.springjpa.domain.request;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BirthCertificatePostRequest {
    int targetSerialNumber;
    String birthReportQualificationsCode;
    String email;
    String phoneNumber;
}
