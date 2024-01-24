package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.HouseholdPostRequest;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.HouseholdNotExistsException;
import com.nhnacademy.springjpa.exception.HouseholdSerialNumberAlreadyExistsException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
    private final HouseholdRepository householdRepository;
    private final ResidentRepository residentRepository;

    public HouseholdService(HouseholdRepository householdRepository, ResidentRepository residentRepository) {
        this.householdRepository = householdRepository;
        this.residentRepository = residentRepository;
    }

    @Transactional
    public Household registerHousehold(HouseholdPostRequest householdPostRequest) {
        int householdSerialNumber = householdPostRequest.getHouseholdSerialNumber();
        Optional<Resident> optionalResident = residentRepository.findById(
                householdPostRequest.getHouseholdResidentSerialNumber());

        if (optionalResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        if (householdRepository.existsById(householdPostRequest.getHouseholdSerialNumber())) {
            throw new HouseholdSerialNumberAlreadyExistsException();
        }

        Household household = new Household();
        household.setHouseholdSerialNumber(householdSerialNumber);
        household.setHouseholdResidentSerialNumber(optionalResident.get());
        household.setHouseholdCompositionDate(LocalDateTime.now());
        household.setHouseholdCompositionReasonCode(householdPostRequest.getHouseholdCompositionReasonCode());
        household.setCurrentHouseMovementAddress(householdPostRequest.getCurrentHouseMovementAddress());

        return householdRepository.save(household);
    }

    public void deleteHousehold(int householdSerialNumber) {
        if (!householdRepository.existsById(householdSerialNumber)) {
            throw new HouseholdNotExistsException();
        }

        householdRepository.deleteById(householdSerialNumber);
    }

    public boolean isHeadOfHousehold(int residentSerialNumber) {
        return householdRepository.findByHouseholdResidentSerialNumber_ResidentSerialNumber(residentSerialNumber)
                .isPresent();
    }

    public int getHouseholdSerialNumberByResidentSerialNumber(int residentSerialNumber) {
        return householdRepository.findByHouseholdResidentSerialNumber_ResidentSerialNumber(residentSerialNumber).get().getHouseholdSerialNumber();
    }
}
