package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.request.HouseholdPostRequest;
import com.nhnacademy.springjpa.entity.Household;
import com.nhnacademy.springjpa.exception.HouseholdSerialNumberAlreadyExistsException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.service.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/household")
public class HouseholdsController {
    private final HouseholdService householdService;

    public HouseholdsController(HouseholdService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<Household> registerHousehold(@RequestBody HouseholdPostRequest householdPostRequest) {
        try {
            householdService.registerHousehold(householdPostRequest);
        } catch (ResidentNotExistsException | HouseholdSerialNumberAlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{householdSerialNumber}")
    public ResponseEntity<Household> deleteHousehold(@PathVariable("householdSerialNumber") int householdSerialNumber) {
        householdService.deleteHousehold(householdSerialNumber);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
