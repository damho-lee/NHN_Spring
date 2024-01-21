package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdCompositionResidentDto {
    String householdRelationshipCode;
    String name;
    String residentRegistrationNumber;
    LocalDateTime reportDate;
    String householdCompositionChangeReasonCode;
}
