package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.FamilyRelationshipDto;
import com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.repository.FamilyRelationshipRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FamilyRelationshipService {
    private final FamilyRelationshipRepository familyRelationshipRepository;
    private final ResidentRepository residentRepository;

    public FamilyRelationshipService(FamilyRelationshipRepository familyRelationshipRepository, ResidentRepository residentRepository) {
        this.familyRelationshipRepository = familyRelationshipRepository;
        this.residentRepository = residentRepository;
    }

    public List<FamilyRelationshipDto> getFamilyRelationShipDtoListByBaseResidentSerialNumber(
            int residentSerialNumber) {
        return familyRelationshipRepository.findFamilyRelationshipDtoListByBaseResidentSerialNumber(residentSerialNumber);
    }

    public List<NameAndResidentRegistrationNumberDto> getParentDtoListByBaseResidentSerialNumber(
            int residentSerialNumber) {
        return familyRelationshipRepository.findByBaseResident_ResidentSerialNumberAndFamilyRelationshipCode(residentSerialNumber);
    }

    public FamilyRelationship saveFamilyRelationship(int baseResidentSerialNumber, int familyResidentSerialNumber,
                                                     String familyRelationshipCode) {
        Optional<Resident> optionalBaseResident = residentRepository.findById(baseResidentSerialNumber);
        Optional<Resident> optionalFamilyResident = residentRepository.findById(familyResidentSerialNumber);

        if (optionalBaseResident.isEmpty() || optionalFamilyResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(baseResidentSerialNumber);
        pk.setFamilyResidentSerialNumber(familyResidentSerialNumber);

        FamilyRelationship familyRelationship = new FamilyRelationship();
        familyRelationship.setPk(pk);
        familyRelationship.setBaseResident(optionalBaseResident.get());
        familyRelationship.setFamilyResident(optionalFamilyResident.get());
        familyRelationship.setFamilyRelationshipCode(familyRelationshipCode);

        return familyRelationshipRepository.save(familyRelationship);
    }

    public void deleteFamilyRelationship(int baseResidentSerialNumber, int familyResidentSerialNumber) {
        Optional<Resident> optionalBaseResident = residentRepository.findById(baseResidentSerialNumber);
        Optional<Resident> optionalFamilyResident = residentRepository.findById(familyResidentSerialNumber);

        if (optionalBaseResident.isEmpty() || optionalFamilyResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        FamilyRelationship.Pk pk = new FamilyRelationship.Pk();
        pk.setBaseResidentSerialNumber(baseResidentSerialNumber);
        pk.setFamilyResidentSerialNumber(familyResidentSerialNumber);

        if (!familyRelationshipRepository.existsById(pk)) {
            throw new FamilyRelationshipNotFoundException();
        }

        familyRelationshipRepository.deleteById(pk);
    }
}




















