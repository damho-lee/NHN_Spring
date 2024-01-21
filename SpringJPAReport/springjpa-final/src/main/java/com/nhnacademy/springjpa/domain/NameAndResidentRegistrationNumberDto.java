package com.nhnacademy.springjpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NameAndResidentRegistrationNumberDto {
    private String name;
    private String residentRegistrationNumber;
}
