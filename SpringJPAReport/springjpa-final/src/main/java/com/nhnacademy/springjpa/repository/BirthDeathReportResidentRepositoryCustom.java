package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.BirthReportDto;
import com.nhnacademy.springjpa.domain.DeathReportDto;

public interface BirthDeathReportResidentRepositoryCustom {
    BirthReportDto getBirthReportDtoByResidentSerialNumber(int residentSerialNumber);
    DeathReportDto getDeathReportDtoByResidentSerialNumber(int residentSerialNumber);
}
