package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.repository.HouseholdMovementAddressRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;

    public HouseholdMovementAddressService(HouseholdMovementAddressRepository householdMovementAddressRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
    }

    public List<HouseholdMovementAddress> getHouseholdMovementAddressesByHouseholdSerialNumber(int householdSerialnumber) {
        return householdMovementAddressRepository.findByPk_HouseholdSerialNumberOrderByPk_HouseMovementReportDateDesc(householdSerialnumber);
    }
}
