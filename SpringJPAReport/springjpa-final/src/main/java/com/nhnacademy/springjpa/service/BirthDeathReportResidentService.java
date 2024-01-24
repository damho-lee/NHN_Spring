package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.request.BirthCertificatePostRequest;
import com.nhnacademy.springjpa.domain.BirthReportDto;
import com.nhnacademy.springjpa.domain.request.DeathCertificatePostRequest;
import com.nhnacademy.springjpa.domain.DeathReportDto;
import com.nhnacademy.springjpa.entity.BirthDeathReportResident;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.exception.BirthDeathReportResidentNotFoundException;
import com.nhnacademy.springjpa.exception.ResidentNotExistsException;
import com.nhnacademy.springjpa.repository.BirthDeathReportResidentRepository;
import com.nhnacademy.springjpa.repository.ResidentRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BirthDeathReportResidentService {
    private final BirthDeathReportResidentRepository birthDeathReportResidentRepository;
    private final ResidentRepository residentRepository;

    public BirthDeathReportResidentService(BirthDeathReportResidentRepository birthDeathReportResidentRepository,
                                           ResidentRepository residentRepository) {
        this.birthDeathReportResidentRepository = birthDeathReportResidentRepository;
        this.residentRepository = residentRepository;
    }

    public BirthReportDto getBirthReportDtoByResidentSerialNumber(int residentSerialNumber) {
        return birthDeathReportResidentRepository.getBirthReportDtoByResidentSerialNumber(residentSerialNumber);
    }

    public int getReportResidentSerialNumber(int residentSerialNumber, String birthDeathTypeCode) {
        return birthDeathReportResidentRepository.findByPk_ResidentSerialNumberAndPk_BirthDeathTypeCode(
                residentSerialNumber,
                birthDeathTypeCode).getReportResident().getResidentSerialNumber();
    }

    public DeathReportDto getDeathReportDtoByResidentSerialNumber(int residentSerialNumber) {
        return birthDeathReportResidentRepository.getDeathReportDtoByResidentSerialNumber(residentSerialNumber);
    }

    public BirthDeathReportResident saveBirthCertificate(int reportResidentSerialNumber,
                                                         BirthCertificatePostRequest birthCertificatePostRequest) {
        int targetResidentSerialNumber = birthCertificatePostRequest.getTargetSerialNumber();
        Optional<Resident> optionalReportResident = residentRepository.findById(reportResidentSerialNumber);
        Optional<Resident> optionalTargetResident = residentRepository.findById(targetResidentSerialNumber);

        if (optionalReportResident.isEmpty() || optionalTargetResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setResidentSerialNumber(targetResidentSerialNumber);
        pk.setBirthDeathTypeCode("출생");

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();

        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setReportResident(optionalReportResident.get());
        birthDeathReportResident.setResident(optionalTargetResident.get());
        birthDeathReportResident.setBirthDeathReportDate(LocalDateTime.now());
        birthDeathReportResident.setBirthReportQualificationsCode(
                birthCertificatePostRequest.getBirthReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(birthCertificatePostRequest.getEmail());
        birthDeathReportResident.setPhoneNumber(birthCertificatePostRequest.getPhoneNumber());

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    public void deleteBirthCertificate(int reportResidentSerialNumber, int targetResidentSerialNumber) {
        Optional<Resident> optionalReportResident = residentRepository.findById(reportResidentSerialNumber);
        Optional<Resident> optionalTargetResident = residentRepository.findById(targetResidentSerialNumber);

        if (optionalReportResident.isEmpty() || optionalTargetResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setResidentSerialNumber(targetResidentSerialNumber);
        pk.setBirthDeathTypeCode("출생");

        if (!birthDeathReportResidentRepository.existsById(pk)) {
            throw new BirthDeathReportResidentNotFoundException();
        }

        birthDeathReportResidentRepository.deleteById(pk);
    }

    public BirthDeathReportResident saveDeathCertificate(int reportResidentSerialNumber,
                                                         DeathCertificatePostRequest deathCertificatePostRequest) {
        int targetResidentSerialNumber = deathCertificatePostRequest.getTargetSerialNumber();
        Optional<Resident> optionalReportResident = residentRepository.findById(reportResidentSerialNumber);
        Optional<Resident> optionalTargetResident = residentRepository.findById(targetResidentSerialNumber);

        if (optionalReportResident.isEmpty() || optionalTargetResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setResidentSerialNumber(targetResidentSerialNumber);
        pk.setBirthDeathTypeCode("사망");

        BirthDeathReportResident birthDeathReportResident = new BirthDeathReportResident();

        birthDeathReportResident.setPk(pk);
        birthDeathReportResident.setReportResident(optionalReportResident.get());
        birthDeathReportResident.setResident(optionalTargetResident.get());
        birthDeathReportResident.setBirthDeathReportDate(LocalDateTime.now());
        birthDeathReportResident.setDeathReportQualificationsCode(
                deathCertificatePostRequest.getDeathReportQualificationsCode());
        birthDeathReportResident.setEmailAddress(deathCertificatePostRequest.getEmail());
        birthDeathReportResident.setPhoneNumber(deathCertificatePostRequest.getPhoneNumber());

        Resident targetResident = optionalTargetResident.get();
        targetResident.setDeathDate(deathCertificatePostRequest.getDeathDate());
        targetResident.setDeathPlaceCode(deathCertificatePostRequest.getDeathPlaceCode());
        targetResident.setDeathPlaceAddress(deathCertificatePostRequest.getDeathPlaceAddress());



        residentRepository.save(targetResident);

        return birthDeathReportResidentRepository.save(birthDeathReportResident);
    }

    public void deleteDeathCertificate(int reportResidentSerialNumber, int targetResidentSerialNumber) {
        Optional<Resident> optionalReportResident = residentRepository.findById(reportResidentSerialNumber);
        Optional<Resident> optionalTargetResident = residentRepository.findById(targetResidentSerialNumber);

        if (optionalReportResident.isEmpty() || optionalTargetResident.isEmpty()) {
            throw new ResidentNotExistsException();
        }

        BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
        pk.setResidentSerialNumber(targetResidentSerialNumber);
        pk.setBirthDeathTypeCode("사망");

        if (!birthDeathReportResidentRepository.existsById(pk)) {
            throw new BirthDeathReportResidentNotFoundException();
        }

        birthDeathReportResidentRepository.deleteById(pk);
    }
}















