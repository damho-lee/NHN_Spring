package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.HouseholdMovementAddressPostRequest;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.HouseholdNotExistsException;
import com.nhnacademy.springjpa.repository.HouseholdMovementAddressRepository;
import com.nhnacademy.springjpa.repository.HouseholdRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class HouseholdMovementAddressService {
    private final HouseholdMovementAddressRepository householdMovementAddressRepository;
    private final HouseholdRepository householdRepository;

    public HouseholdMovementAddressService(HouseholdMovementAddressRepository householdMovementAddressRepository,
                                           HouseholdRepository householdRepository) {
        this.householdMovementAddressRepository = householdMovementAddressRepository;
        this.householdRepository = householdRepository;
    }

    public List<HouseholdMovementAddress> getHouseholdMovementAddressesByHouseholdSerialNumber(
            int householdSerialnumber) {
        return householdMovementAddressRepository.findByPk_HouseholdSerialNumberOrderByPk_HouseMovementReportDateDesc(
                householdSerialnumber);
    }

    @Transactional
    public HouseholdMovementAddress saveHouseholdMovementAddress(int householdSerialNumber,
                                                                 HouseholdMovementAddressPostRequest householdMovementAddressPostRequest) {
        Optional<Household> optionalHousehold = householdRepository.findById(householdSerialNumber);
        if (optionalHousehold.isEmpty()) {
            throw new HouseholdNotExistsException();
        }

        HouseholdMovementAddress householdMovementAddress = new HouseholdMovementAddress();
        householdMovementAddress.setPk(new HouseholdMovementAddress.Pk(
                householdSerialNumber,
                LocalDateTime.now()
        ));
        householdMovementAddress.setHousehold(optionalHousehold.get());
        householdMovementAddress.setHouseMovementAddress(householdMovementAddressPostRequest.getHouseMovementAddress());
        householdMovementAddress.setLastAddress("Y");

        Optional<HouseholdMovementAddress> optionalHouseholdMovementAddress =
                householdMovementAddressRepository.findByPk_HouseholdSerialNumberAndLastAddress(householdSerialNumber,
                        "Y");

        householdMovementAddress = householdMovementAddressRepository.save(householdMovementAddress);

        if (optionalHouseholdMovementAddress.isPresent()) {
            HouseholdMovementAddress householdMovementAddressForChange = optionalHouseholdMovementAddress.get();
            householdMovementAddressForChange.setLastAddress("N");
            householdMovementAddressRepository.save(householdMovementAddressForChange);
        }

        return householdMovementAddress;
    }

    public HouseholdMovementAddress updateHouseholdMovementAddress(int householdSerialNumber, LocalDateTime reportDate,
                                                                   HouseholdMovementAddressPostRequest householdMovementAddressPostRequest) {
        Optional<HouseholdMovementAddress> optionalHouseholdMovementAddress
                = householdMovementAddressRepository.findById(
                new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate));

        if (optionalHouseholdMovementAddress.isEmpty()) {
            throw new HouseholdNotExistsException();
        }

        HouseholdMovementAddress householdMovementAddress = optionalHouseholdMovementAddress.get();
        householdMovementAddress.setHouseMovementAddress(householdMovementAddressPostRequest.getHouseMovementAddress());
        return householdMovementAddressRepository.save(householdMovementAddress);
    }


    public void deleteHouseholdMovementAddress(int householdSerialNumber, LocalDateTime reportDate) {
        Optional<HouseholdMovementAddress> optionalHouseholdMovementAddress
                = householdMovementAddressRepository.findById(
                new HouseholdMovementAddress.Pk(householdSerialNumber, reportDate));

        if (optionalHouseholdMovementAddress.isEmpty()) {
            throw new HouseholdNotExistsException();
        }

        householdMovementAddressRepository.delete(optionalHouseholdMovementAddress.get());
    }
}
