package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.CertificateIssueDto;
import com.nhnacademy.springjpa.domain.FamilyRelationshipDto;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.CertificateIssueService;
import com.nhnacademy.springjpa.service.FamilyRelationshipService;
import com.nhnacademy.springjpa.service.ResidentService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/familyRelationship")
public class FamilyRelationshipController {
    private final FamilyRelationshipService familyRelationshipService;
    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;

    public FamilyRelationshipController(FamilyRelationshipService familyRelationshipService,
                                        CertificateIssueService certificateIssueService,
                                        ResidentService residentService) {
        this.familyRelationshipService = familyRelationshipService;
        this.certificateIssueService = certificateIssueService;
        this.residentService = residentService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getFamilyRelationshipCertificatePage(@PathVariable("residentSerialNumber") int residentSerialNumber,
                                                       HttpServletRequest request,
                                                       Model model) {
        Resident resident = residentService.getResidentByResidentSerialNumber(residentSerialNumber);

        long certificateConfirmationNumber =
                Long.parseLong(request.getAttribute("certificateConfirmationNumber").toString());
        CertificateIssueDto certificateIssue =
                certificateIssueService.getCertificateIssueDtoByCertificateConfirmationNumber(
                        certificateConfirmationNumber);

        FamilyRelationshipDto me = new FamilyRelationshipDto();
        me.setFamilyRelationshipCode("본인");
        me.setName(resident.getName());
        me.setBirthDate(resident.getBirthDate());
        me.setResidentRegistrationNumber(resident.getResidentRegistrationNumber());
        me.setGender(resident.getGender());

        List<FamilyRelationshipDto> familyRelationshipDtoList =
                familyRelationshipService.getFamilyRelationShipDtoListByBaseResidentSerialNumber(residentSerialNumber);

        String currentHouseMovementAddress = resident.getRegistrationBaseAddress();

        familyRelationshipDtoList.add(0, me);

        model.addAttribute("certificateIssueDate", certificateIssue.getCertificateIssueDate());
        model.addAttribute("certificateConfirmationNumber", certificateConfirmationNumber);
        model.addAttribute("currentHouseMovementAddress", currentHouseMovementAddress);
        model.addAttribute("familyRelationships", familyRelationshipDtoList);
        return "familyRelationsCertificate";
    }
}
