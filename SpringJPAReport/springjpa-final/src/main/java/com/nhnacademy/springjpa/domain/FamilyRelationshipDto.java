package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationshipDto {
    String familyRelationshipCode;
    String name;
    LocalDateTime birthDate;
    String residentRegistrationNumber;
    String gender;
}
