package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.FamilyRelationshipDto;
import com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FamilyRelationshipRepository extends JpaRepository<FamilyRelationship, FamilyRelationship.Pk> {
    @Query("select new com.nhnacademy.springjpa.domain.FamilyRelationshipDto( fr.familyRelationshipCode, r.name, r.birthDate, r.residentRegistrationNumber, r.gender) " +
            "from FamilyRelationship fr left join Resident r on fr.pk.familyResidentSerialNumber = r.residentSerialNumber " +
            "where fr.baseResident.residentSerialNumber = ?1 " +
            "group by fr.familyRelationshipCode, r.name, r.birthDate, r.residentRegistrationNumber, r.gender")
    List<FamilyRelationshipDto> findFamilyRelationshipDtoListByBaseResidentSerialNumber(int residentSerialNumber);

    @Query("select new com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto(r.name, r.residentRegistrationNumber) " +
            "from FamilyRelationship fr left join Resident r on fr.pk.familyResidentSerialNumber = r.residentSerialNumber " +
            "where fr.baseResident.residentSerialNumber = ?1 and (fr.familyRelationshipCode = '부' or fr.familyRelationshipCode = '모') " +
            "group by r.name, r.residentRegistrationNumber, fr.familyRelationshipCode " +
            "order by fr.familyRelationshipCode desc")
    List<NameAndResidentRegistrationNumberDto> findByBaseResident_ResidentSerialNumberAndFamilyRelationshipCode(int residentSerialNumber);
}
/*
select r.name, r.resident_registration_number
from family_relationship fr
left join resident r on fr.family_resident_serial_number = r.resident_serial_number
where fr.base_resident_serial_number = 7 and (fr.family_relationship_code = '부' or fr.family_relationship_code = '모')
group by r.name, r.resident_registration_number, fr.family_relationship_code
order by fr.family_relationship_code desc;
 */