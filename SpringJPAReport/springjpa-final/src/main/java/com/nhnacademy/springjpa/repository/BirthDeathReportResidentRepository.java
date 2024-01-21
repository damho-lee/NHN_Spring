package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDeathReportResidentRepository extends
        JpaRepository<BirthDeathReportResident, BirthDeathReportResident.Pk>, BirthDeathReportResidentRepositoryCustom {
    BirthDeathReportResident findByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(int residentSerialNumber,
                                                                                   String birthDeathTypeCode);
}
