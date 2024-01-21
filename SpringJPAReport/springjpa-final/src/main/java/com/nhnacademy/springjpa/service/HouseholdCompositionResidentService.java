package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.repository.HouseholdCompositionResidentRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseholdCompositionResidentService {
    private final HouseholdCompositionResidentRepository repository;

    public HouseholdCompositionResidentService(HouseholdCompositionResidentRepository repository) {
        this.repository = repository;
    }

    public int getHouseholdSerialNumberByResidentSerialNumber(int residentSerialNumber) {
        return repository.findByPk_ResidentSerialNumber(residentSerialNumber).getPk().getHouseholdSerialNumber();
    }

    public List<HouseholdCompositionResidentDto> getHouseholdCompositionResidentsByHouseholdSerialNumber(int householdSerialNumber) {
        return repository.findHouseholdCompositionResidentDtoListByHouseholdSerialNumber(householdSerialNumber);
    }
}
