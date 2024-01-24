package com.nhnacademy.springjpa.domain.request;

import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
public class HouseholdMovementAddressPostRequest {
    String houseMovementAddress;
}
