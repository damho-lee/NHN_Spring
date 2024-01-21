package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.ResidentRegisterPostRequest;
import com.nhnacademy.springjpa.domain.ResidentRegisterPutRequest;
import com.nhnacademy.springjpa.entity.FamilyRelationship;
import com.nhnacademy.springjpa.exception.FamilyRelationshipNotFoundException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.service.FamilyRelationshipService;
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
@RequestMapping("/residents")
public class FamilyRelationshipsController {
    private final FamilyRelationshipService familyRelationshipService;

    public FamilyRelationshipsController(FamilyRelationshipService familyRelationshipService) {
        this.familyRelationshipService = familyRelationshipService;
    }

    @PostMapping("/{serialNumber}/relationship")
    public ResponseEntity<FamilyRelationship> registerFamilyRelationship(
            @PathVariable("serialNumber") int baseResidentSerialNumber,
            @RequestBody ResidentRegisterPostRequest residentRegisterPostRequest) {
        FamilyRelationship familyRelationship;
        try {
            familyRelationship = familyRelationshipService.saveFamilyRelationship(
                    baseResidentSerialNumber,
                    residentRegisterPostRequest.getFamilySerialNumber(),
                    residentRegisterPostRequest.getRelationShip()
            );
        } catch (ResidentNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(familyRelationship, HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> updateFamilyRelationship(
            @PathVariable("serialNumber") int baseResidentSerialNumber,
            @PathVariable("familySerialNumber") int familyResidentSerialNumber,
            @RequestBody ResidentRegisterPutRequest relationShip) {
        try {
            familyRelationshipService.saveFamilyRelationship(
                    baseResidentSerialNumber,
                    familyResidentSerialNumber,
                    relationShip.getRelationShip()
            );
        } catch (ResidentNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{serialNumber}/relationship/{familySerialNumber}")
    public ResponseEntity<FamilyRelationship> deleteFamilyRelationship(
            @PathVariable("serialNumber") int baseResidentSerialNumber,
            @PathVariable("familySerialNumber") int familyResidentSerialNumber) {
        try {
            familyRelationshipService.deleteFamilyRelationship(baseResidentSerialNumber, familyResidentSerialNumber);
        } catch (ResidentNotExistsException | FamilyRelationshipNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}












