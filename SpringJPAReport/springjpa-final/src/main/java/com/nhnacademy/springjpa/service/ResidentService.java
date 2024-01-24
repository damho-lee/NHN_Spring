package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto;
import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.domain.request.ResidentPostRequest;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.GenderValidationException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.exception.ResidentSerialNumberAlreadyExistsException;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public Page<ResidentDto> getResidents(Pageable pageable) {
        return residentRepository.findResidentDtoForIndexPage(pageable);
    }

    public Resident getResidentByResidentSerialNumber(int residentSerialNumber) {
        return residentRepository.findByResidentSerialNumber(residentSerialNumber);
    }

    public Resident saveResident(ResidentPostRequest residentPostRequest) {
        if (residentRepository.existsById(residentPostRequest.getResidentSerialNumber())) {
            throw new ResidentSerialNumberAlreadyExistsException();
        }

        if (!"남".equals(residentPostRequest.getGender()) && !"여".equals(residentPostRequest.getGender())) {
            throw new GenderValidationException();
        }

        Resident resident = new Resident();
        resident.setResidentSerialNumber(residentPostRequest.getResidentSerialNumber());
        resident.setName(residentPostRequest.getName());
        resident.setResidentRegistrationNumber(residentPostRequest.getResidentRegistrationNumber());
        resident.setGender(residentPostRequest.getGender());
        resident.setBirthDate(residentPostRequest.getBirthDate());
        resident.setBirthPlaceCode(residentPostRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentPostRequest.getRegistrationBaseAddress());

        return residentRepository.save(resident);
    }

    public Resident updateResident(int residentSerialNumber, ResidentPostRequest residentPostRequest) {
        if (!residentRepository.existsById(residentSerialNumber)) {
            throw new ResidentNotExistsException();
        }

        if (!"남".equals(residentPostRequest.getGender()) && !"여".equals(residentPostRequest.getGender())) {
            throw new GenderValidationException();
        }

        Resident resident = new Resident();
        resident.setResidentSerialNumber(residentSerialNumber);
        resident.setName(residentPostRequest.getName());
        resident.setResidentRegistrationNumber(residentPostRequest.getResidentRegistrationNumber());
        resident.setGender(residentPostRequest.getGender());
        resident.setBirthDate(residentPostRequest.getBirthDate());
        resident.setBirthPlaceCode(residentPostRequest.getBirthPlaceCode());
        resident.setRegistrationBaseAddress(residentPostRequest.getRegistrationBaseAddress());

        return residentRepository.save(resident);
    }

    public NameAndResidentRegistrationNumberDto getNameAndResidentRegistrationNumberDtoByResidentSerialNumber(
            int residentSerialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(residentSerialNumber);

        return new NameAndResidentRegistrationNumberDto(resident.getName(), resident.getResidentRegistrationNumber());
    }

    @Transactional
    public void deleteResident(int residentSerialNumber) {
        residentRepository.deleteByResidentSerialNumber(residentSerialNumber);
    }
}
