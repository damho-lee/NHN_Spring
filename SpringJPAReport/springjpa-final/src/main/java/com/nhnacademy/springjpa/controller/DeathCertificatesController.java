package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class DeathCertificatesController {
    @PostMapping("/{serialNumber}/death")
    public ResponseEntity<BirthDeathReportResident> registerDeathCertificate(
            @PathVariable("serialNumber") int reportResidentSerialNumber) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
