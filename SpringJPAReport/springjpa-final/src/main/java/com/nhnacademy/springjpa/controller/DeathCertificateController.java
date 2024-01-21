package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.DeathReportDto;
import com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto;
import com.nhnacademy.springjpa.service.BirthDeathReportResidentService;
import com.nhnacademy.springjpa.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deathReport")
public class DeathCertificateController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;
    private final ResidentService residentService;

    public DeathCertificateController(BirthDeathReportResidentService birthDeathReportResidentService,
                                      ResidentService residentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.residentService = residentService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getDeathReportPage(@PathVariable("residentSerialNumber") int residentSerialNumber,
                                     Model model) {
        DeathReportDto deathReportDto =
                birthDeathReportResidentService.getDeathReportDtoByResidentSerialNumber(residentSerialNumber);

        int reportResidentSerialNumber = birthDeathReportResidentService.getReportResidentSerialNumber(residentSerialNumber, "사망");
        NameAndResidentRegistrationNumberDto reporterDto =
                residentService.getNameAndResidentRegistrationNumberDtoByResidentSerialNumber(reportResidentSerialNumber);

        model.addAttribute("reporter", reporterDto);
        model.addAttribute("deathReportDto", deathReportDto);
        return "deathCertificate";
    }
}
