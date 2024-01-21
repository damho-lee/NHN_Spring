package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.CertificateIssueDto;
import com.nhnacademy.springjpa.domain.HeadOfHousehold;
import com.nhnacademy.springjpa.domain.HouseholdCompositionResidentDto;
import com.nhnacademy.springjpa.entity.HouseholdMovementAddress;
import com.nhnacademy.springjpa.exception.NoHeadOfHouseholdException;
import com.nhnacademy.springjpa.service.CertificateIssueService;
import com.nhnacademy.springjpa.service.HouseholdCompositionResidentService;
import com.nhnacademy.springjpa.service.HouseholdMovementAddressService;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residentRegister")
public class ResidentRegisterController {
    private final HouseholdCompositionResidentService householdCompositionResidentService;
    private final HouseholdMovementAddressService householdMovementAddressService;
    private final CertificateIssueService certificateIssueService;

    public ResidentRegisterController(HouseholdCompositionResidentService householdCompositionResidentService,
                                      HouseholdMovementAddressService householdMovementAddressService,
                                      CertificateIssueService certificateIssueService) {
        this.householdCompositionResidentService = householdCompositionResidentService;
        this.householdMovementAddressService = householdMovementAddressService;
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getResidentRegisterPage(@PathVariable("residentSerialNumber") int residentSerialNumber,
                                          HttpServletRequest request,
                                          Model model) {
        long certificateConfirmationNumber =
                Long.parseLong(request.getAttribute("certificateConfirmationNumber").toString());
        CertificateIssueDto certificateIssue =
                certificateIssueService.getCertificateIssueDtoByCertificateConfirmationNumber(
                        certificateConfirmationNumber);

        int householdSerialNumber = householdCompositionResidentService
                .getHouseholdSerialNumberByResidentSerialNumber(residentSerialNumber);

        List<HouseholdCompositionResidentDto> householdCompositionREsidentDtoList
                = householdCompositionResidentService.getHouseholdCompositionResidentsByHouseholdSerialNumber(
                householdSerialNumber);

        HeadOfHousehold headOfHousehold = findHeadOfHousehold(householdCompositionREsidentDtoList);

        List<HouseholdMovementAddress> householdMovementAddresses
                = householdMovementAddressService.getHouseholdMovementAddressesByHouseholdSerialNumber(
                householdSerialNumber);

        model.addAttribute("certificateIssueDate", certificateIssue.getCertificateIssueDate());
        model.addAttribute("certificateConfirmationNumber", certificateConfirmationNumber);
        model.addAttribute("headOfHousehold", headOfHousehold);
        model.addAttribute("householdMovementAddresses", householdMovementAddresses);
        model.addAttribute("householdCompositionResidents", householdCompositionREsidentDtoList);

        return "residentRegisterCertificate";
    }

    private HeadOfHousehold findHeadOfHousehold(
            List<HouseholdCompositionResidentDto> householdCompositionResidentDtoList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (HouseholdCompositionResidentDto householdCompositionResidentDto : householdCompositionResidentDtoList) {
            if ("본인".equals(householdCompositionResidentDto.getHouseholdRelationshipCode())) {
                return new HeadOfHousehold(householdCompositionResidentDto.getName(),
                        householdCompositionResidentDto.getHouseholdCompositionChangeReasonCode()
                                .concat(" ")
                                .concat(householdCompositionResidentDto.getReportDate().format(formatter)));

            }
        }

        throw new NoHeadOfHouseholdException();
    }
}
