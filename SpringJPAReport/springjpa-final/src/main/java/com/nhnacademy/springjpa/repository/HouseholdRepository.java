package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Household;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Integer> {
    Household findByHouseholdSerialNumber(int householdSerialNumber);
    Optional<Household> findByHouseholdResidentSerialNumber_ResidentSerialNumber(int residentSerialNumber);
}
