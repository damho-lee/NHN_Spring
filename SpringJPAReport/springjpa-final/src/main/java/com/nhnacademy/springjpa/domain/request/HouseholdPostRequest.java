package com.nhnacademy.springjpa.domain.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class HouseholdPostRequest {
    int householdSerialNumber;
    int householdResidentSerialNumber;
    LocalDateTime householdCompositionDate;
    String householdCompositionReasonCode;
    String currentHouseMovementAddress;
}
