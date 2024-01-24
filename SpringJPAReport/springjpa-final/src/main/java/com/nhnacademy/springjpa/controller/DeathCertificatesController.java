package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.request.DeathCertificatePostRequest;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.service.BirthDeathReportResidentService;
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
public class DeathCertificatesController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public DeathCertificatesController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/{serialNumber}/death")
    public ResponseEntity<BirthDeathReportResident> registerDeathCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber,
            @RequestBody DeathCertificatePostRequest deathCertificatePostRequest) {
        try {
            birthDeathReportResidentService.saveDeathCertificate(
                    reportResidentSerialNumber, deathCertificatePostRequest);
        } catch (ResidentNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> updateDeathCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber,
            @PathVariable("targetSerialNumber") int targetResidentSerialNumber,
            @RequestBody DeathCertificatePostRequest deathCertificatePostRequest) {
        try {
            DeathCertificatePostRequest newDeathCertificatePostRequest
                    = DeathCertificatePostRequest.builder()
                    .targetSerialNumber(targetResidentSerialNumber)
                    .deathReportQualificationsCode(deathCertificatePostRequest.getDeathReportQualificationsCode())
                    .email(deathCertificatePostRequest.getEmail())
                    .phoneNumber(deathCertificatePostRequest.getPhoneNumber())
                    .deathDate(deathCertificatePostRequest.getDeathDate())
                    .deathPlaceCode(deathCertificatePostRequest.getDeathPlaceCode())
                    .deathPlaceAddress(deathCertificatePostRequest.getDeathPlaceAddress())
                    .build();

            birthDeathReportResidentService.saveDeathCertificate(
                    reportResidentSerialNumber, newDeathCertificatePostRequest);
        } catch (ResidentNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{serialNumber}/death/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> deleteDeathCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber,
            @PathVariable("targetSerialNumber") int targetResidentSerialNumber) {
        try {
            birthDeathReportResidentService.deleteDeathCertificate(reportResidentSerialNumber, targetResidentSerialNumber);
        } catch (ResidentNotExistsException | BirthDeathReportResidentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
