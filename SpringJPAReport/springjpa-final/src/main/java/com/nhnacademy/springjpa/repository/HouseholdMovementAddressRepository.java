package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMovementAddressRepository
        extends JpaRepository<HouseholdMovementAddress, HouseholdMovementAddress.Pk> {
    List<HouseholdMovementAddress> findByPk_HouseholdSerialNumberOrderByPk_HouseMovementReportDateDesc(int householdSerialNumber);
    Optional<HouseholdMovementAddress> findByPk_HouseholdSerialNumberAndLastAddress(int householdSerialNumber, String isLastAddress);
}
