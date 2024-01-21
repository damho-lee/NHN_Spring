package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.BirthReportDto;
import com.nhnacademy.springjpa.domain.DeathReportDto;
import com.nhnacademy.springjpa.entity.QBirthDeathReportResident;
import com.nhnacademy.springjpa.entity.QResident;
import com.querydsl.core.types.Projections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class BirthDeathReportResidentRepositoryImpl
        extends QuerydslRepositorySupport implements BirthDeathReportResidentRepositoryCustom {
    public BirthDeathReportResidentRepositoryImpl() {
        super(BirthReportDto.class);
    }

    @Override
    public BirthReportDto getBirthReportDtoByResidentSerialNumber(int residentSerialNumber) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;

        return from(birthDeathReportResident)
                .leftJoin(birthDeathReportResident.resident, resident)
                .on(birthDeathReportResident.resident.residentSerialNumber.eq(resident.residentSerialNumber))
                .where(birthDeathReportResident.resident.residentSerialNumber.eq(residentSerialNumber))
                .where(birthDeathReportResident.pk.birthDeathTypeCode.eq("출생"))
                .select(Projections.constructor(
                        BirthReportDto.class,
                        birthDeathReportResident.birthDeathReportDate,
                        resident.name,
                        resident.gender,
                        resident.birthDate,
                        resident.birthPlaceCode,
                        resident.registrationBaseAddress,
                        birthDeathReportResident.birthReportQualificationsCode,
                        birthDeathReportResident.emailAddress,
                        birthDeathReportResident.phoneNumber
                ))
                .fetchOne();
    }

    @Override
    public DeathReportDto getDeathReportDtoByResidentSerialNumber(int residentSerialNumber) {
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QResident resident = QResident.resident;

        return from(birthDeathReportResident)
                .leftJoin(birthDeathReportResident.resident, resident)
                .on(birthDeathReportResident.resident.residentSerialNumber.eq(resident.residentSerialNumber))
                .where(birthDeathReportResident.resident.residentSerialNumber.eq(residentSerialNumber))
                .where(birthDeathReportResident.pk.birthDeathTypeCode.eq("사망"))
                .select(Projections.constructor(
                        DeathReportDto.class,
                        birthDeathReportResident.birthDeathReportDate,
                        resident.name,
                        resident.residentRegistrationNumber,
                        resident.deathDate,
                        resident.deathPlaceCode,
                        resident.deathPlaceAddress,
                        birthDeathReportResident.deathReportQualificationsCode,
                        birthDeathReportResident.emailAddress,
                        birthDeathReportResident.phoneNumber
                ))
                .fetchOne();
    }
}
