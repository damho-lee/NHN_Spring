package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto;
import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public NameAndResidentRegistrationNumberDto getNameAndResidentRegistrationNumberDtoByResidentSerialNumber(
            int residentSerialNumber) {
        Resident resident = residentRepository.findByResidentSerialNumber(residentSerialNumber);

        return new NameAndResidentRegistrationNumberDto(resident.getName(), resident.getResidentRegistrationNumber());
    }
}
