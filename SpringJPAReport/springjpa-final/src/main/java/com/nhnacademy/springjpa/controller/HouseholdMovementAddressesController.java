package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.request.HouseholdMovementAddressPostRequest;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.HouseholdNotExistsException;
import com.nhnacademy.springjpa.service.HouseholdMovementAddressService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class HouseholdMovementAddressesController {
    private final HouseholdMovementAddressService householdMovementAddressService;

    public HouseholdMovementAddressesController(HouseholdMovementAddressService householdMovementAddressService) {
        this.householdMovementAddressService = householdMovementAddressService;
    }

    @PostMapping("/{householdSerialNumber}/movement")
    public ResponseEntity<HouseholdMovementAddress> registerHouseholdMovementAddress(
            @PathVariable("householdSerialNumber") int householdSerialNumber,
            @RequestBody HouseholdMovementAddressPostRequest householdMovementAddressPostRequest) {
        try {
            householdMovementAddressService.saveHouseholdMovementAddress(
                    householdSerialNumber, householdMovementAddressPostRequest);
        } catch (HouseholdNotExistsException | DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddress> updateHouseholdMovementAddress(
            @PathVariable("householdSerialNumber") int householdSerialNumber,
            @PathVariable("reportDate") String reportDateString,
            @RequestBody HouseholdMovementAddressPostRequest householdMovementAddressPostRequest) {
        LocalDate localDate = LocalDate.parse(reportDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime reportDate = localDate.atStartOfDay();
        try {
            householdMovementAddressService.updateHouseholdMovementAddress(
                    householdSerialNumber, reportDate, householdMovementAddressPostRequest);
        } catch (HouseholdNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{householdSerialNumber}/movement/{reportDate}")
    public ResponseEntity<HouseholdMovementAddress> deleteHouseholdMovementAddress(
            @PathVariable("householdSerialNumber") int householdSerialNumber,
            @PathVariable("reportDate") String reportDateString) {
        LocalDate localDate = LocalDate.parse(reportDateString, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDateTime reportDate = localDate.atStartOfDay();

        try {
            householdMovementAddressService.deleteHouseholdMovementAddress(householdSerialNumber, reportDate);
        } catch(HouseholdNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}