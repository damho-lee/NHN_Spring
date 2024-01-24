package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.repository.HouseholdCompositionResidentRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HouseholdCompositionResidentService {
    private final HouseholdCompositionResidentRepository householdCompositionResidentRepository;

    public HouseholdCompositionResidentService(HouseholdCompositionResidentRepository householdCompositionResidentRepository) {
        this.householdCompositionResidentRepository = householdCompositionResidentRepository;
    }

    public int getHouseholdSerialNumberByResidentSerialNumber(int residentSerialNumber) {
        return householdCompositionResidentRepository.findByPk_ResidentSerialNumber(residentSerialNumber).getPk().getHouseholdSerialNumber();
    }

    public List<HouseholdCompositionResidentDto> getHouseholdCompositionResidentsByHouseholdSerialNumber(int householdSerialNumber) {
        return householdCompositionResidentRepository.findHouseholdCompositionResidentDtoListByHouseholdSerialNumber(householdSerialNumber);
    }

    public boolean isAlone(int residentSerialNumber) {
        return 1 == householdCompositionResidentRepository.countByPk_HouseholdSerialNumber(residentSerialNumber);
    }
}
