package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.repository.HouseholdRepository;
import org.springframework.stereotype.Service;

@Service
public class HouseholdService {
    private final HouseholdRepository repository;

    public HouseholdService(HouseholdRepository repository) {
        this.repository = repository;
    }

    public String getCurrentHouseMovementAddressByHouseholdSerialNumber(int householdSerialNumber) {
        return repository.findByHouseholdSerialNumber(householdSerialNumber).getCurrentHouseMovementAddress();
    }
}
