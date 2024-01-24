package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.request.BirthCertificatePostRequest;
import com.nhnacademy.springjpa.domain.request.BirthCertificatePutRequest;
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
public class BirthCertificatesController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;

    public BirthCertificatesController(BirthDeathReportResidentService birthDeathReportResidentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
    }

    @PostMapping("/{serialNumber}/birth")
    public ResponseEntity<BirthDeathReportResident> registerBrithCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber,
            @RequestBody BirthCertificatePostRequest birthCertificatePostRequest) {
        try {
            birthDeathReportResidentService.saveBirthCertificate(
                    reportResidentSerialNumber, birthCertificatePostRequest);
        } catch (ResidentNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> updateBirthCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber,
            @PathVariable("targetSerialNumber") int targetResidentSerialNumber,
            @RequestBody BirthCertificatePutRequest birthCertificatePutRequest) {
        BirthCertificatePostRequest birthCertificatePostRequest = new BirthCertificatePostRequest(
                targetResidentSerialNumber,
                birthCertificatePutRequest.getBirthReportQualificationsCode(),
                birthCertificatePutRequest.getEmail(),
                birthCertificatePutRequest.getPhoneNumber()
        );
        try {
            birthDeathReportResidentService.saveBirthCertificate(reportResidentSerialNumber, birthCertificatePostRequest);
        } catch(ResidentNotExistsException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{serialNumber}/birth/{targetSerialNumber}")
    public ResponseEntity<BirthDeathReportResident> deleteBirthCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber,
            @PathVariable("targetSerialNumber") int targetResidentSerialNumber) {
        try {
            birthDeathReportResidentService.deleteBirthCertificate(reportResidentSerialNumber, targetResidentSerialNumber);
        } catch (ResidentNotExistsException | BirthDeathReportResidentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



















