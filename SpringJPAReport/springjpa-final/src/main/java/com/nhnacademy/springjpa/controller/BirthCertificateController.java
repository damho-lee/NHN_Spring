package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.BirthReportDto;
import com.nhnacademy.springjpa.domain.NameAndResidentRegistrationNumberDto;
import com.nhnacademy.springjpa.service.BirthDeathReportResidentService;
import com.nhnacademy.springjpa.service.FamilyRelationshipService;
import com.nhnacademy.springjpa.service.ResidentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/birthReport")
public class BirthCertificateController {
    private final BirthDeathReportResidentService birthDeathReportResidentService;
    private final FamilyRelationshipService familyRelationshipService;
    private final ResidentService residentService;

    public BirthCertificateController(BirthDeathReportResidentService birthDeathReportResidentService,
                                      FamilyRelationshipService familyRelationshipService,
                                      ResidentService residentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.familyRelationshipService = familyRelationshipService;
        this.residentService = residentService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getBirthReportPage(@PathVariable("residentSerialNumber") int residentSerialNumber,
                                     Model model) {
        BirthReportDto birthReportDto =
                birthDeathReportResidentService.getBirthReportDtoByResidentSerialNumber(residentSerialNumber);

        List<NameAndResidentRegistrationNumberDto> parentDtoList =
                familyRelationshipService.getParentDtoListByBaseResidentSerialNumber(residentSerialNumber);

        int reportResidentSerialNumber =
                birthDeathReportResidentService.getReportResidentSerialNumber(residentSerialNumber, "출생");
        NameAndResidentRegistrationNumberDto reporterDto =
                residentService.getNameAndResidentRegistrationNumberDtoByResidentSerialNumber(
                        reportResidentSerialNumber);

        model.addAttribute("reporter", reporterDto);
        model.addAttribute("father", parentDtoList.get(0));
        model.addAttribute("mother", parentDtoList.get(1));
        model.addAttribute("birthReportDto", birthReportDto);
        return "birthCertificate";
    }
}
