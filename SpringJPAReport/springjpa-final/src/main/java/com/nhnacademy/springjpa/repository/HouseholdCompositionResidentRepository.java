package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.HouseholdCompositionResident;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdCompositionResidentRepository
        extends JpaRepository<HouseholdCompositionResident, HouseholdCompositionResident.Pk> {
    HouseholdCompositionResident findByPk_ResidentSerialNumber(int residentSerialNumber);

    @Query("select new com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto(hcr.householdRelationshipCode, r.name, r.residentRegistrationNumber, hcr.reportDate, hcr.householdCompositionChangeReasonCode) " +
            "from HouseholdCompositionResident hcr left join Resident r on hcr.resident.residentRegistrationNumber = r.residentRegistrationNumber " +
            "where hcr.pk.householdSerialNumber = ?1 " +
            "group by hcr.householdRelationshipCode, r.name, r.residentRegistrationNumber, hcr.reportDate, hcr.householdCompositionChangeReasonCode " +
            "order by hcr.reportDate")
    List<HouseholdCompositionResidentDto> findHouseholdCompositionResidentDtoListByHouseholdSerialNumber(int householdSerialNumber);

    int countByPk_HouseholdSerialNumber(int householdSerialNumber);
}