package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.request.ResidentPostRequest;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.GenderValidationException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.exception.ResidentSerialNumberAlreadyExistsException;
import com.nhnacademy.springjpa.service.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class ResidentRegistersController {
    private final ResidentService residentService;

    public ResidentRegistersController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public ResponseEntity<Resident> registerResident(@RequestBody ResidentPostRequest residentPostRequest) {
        try {
            residentService.saveResident(residentPostRequest);
        } catch (ResidentSerialNumberAlreadyExistsException | GenderValidationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{serialNumber}")
    public ResponseEntity<Resident> updateResident(@PathVariable int serialNumber,
                                                   @RequestBody ResidentPostRequest residentPostRequest) {
        try {
            residentService.updateResident(serialNumber, residentPostRequest);
        } catch (ResidentNotExistsException | GenderValidationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
