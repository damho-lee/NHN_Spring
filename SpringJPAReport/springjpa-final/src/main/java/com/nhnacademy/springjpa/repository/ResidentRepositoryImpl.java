package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.QBirthDeathReportResident;
import com.nhnacademy.springjpa.entity.QCertificateIssue;
import com.nhnacademy.springjpa.entity.QHousehold;
import com.nhnacademy.springjpa.entity.QHouseholdCompositionResident;
import com.nhnacademy.springjpa.entity.QResident;
import com.querydsl.core.types.Projections;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ResidentRepositoryImpl extends QuerydslRepositorySupport implements ResidentRepositoryCustom {

    public ResidentRepositoryImpl() {
        super(ResidentDto.class);
    }

    @Override
    public Page<ResidentDto> findResidentDtoForIndexPage(Pageable pageable) {
        QResident resident = QResident.resident;
        QHouseholdCompositionResident householdCompositionResident =
                QHouseholdCompositionResident.householdCompositionResident;
        QHousehold household = QHousehold.household;
        QBirthDeathReportResident birthDeathReportResident = QBirthDeathReportResident.birthDeathReportResident;
        QCertificateIssue certificateIssue = QCertificateIssue.certificateIssue;

        List<ResidentDto> content = from(resident)
                .leftJoin(resident.householdCompositionResidents, householdCompositionResident)
                .leftJoin(resident.households, household)
                .leftJoin(resident.birthDeathReportResidents, birthDeathReportResident)
                .leftJoin(resident.certificateIssues, certificateIssue)
                .select(Projections.constructor(ResidentDto.class,
                        resident.residentSerialNumber,
                        resident.name,
                        householdCompositionResident.pk.householdSerialNumber,
                        birthDeathReportResident.pk.birthDeathTypeCode,
                        certificateIssue.certificateTypeCode.count()))
                .groupBy(resident.residentSerialNumber,
                        resident.name,
                        householdCompositionResident.pk.householdSerialNumber,
                        birthDeathReportResident.pk.birthDeathTypeCode)
                .orderBy(resident.residentSerialNumber.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = from(resident)
                .fetchCount();

        return new PageImpl<>(content, pageable, totalCount);
    }

}
