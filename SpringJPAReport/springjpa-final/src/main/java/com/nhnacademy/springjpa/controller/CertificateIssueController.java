package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.CertificateIssueDto;
import com.nhnacademy.springjpa.service.CertificateIssueService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/certificateIssue")
public class CertificateIssueController {
    private final CertificateIssueService certificateIssueService;

    public CertificateIssueController(CertificateIssueService certificateIssueService) {
        this.certificateIssueService = certificateIssueService;
    }

    @GetMapping("/{residentSerialNumber}")
    public String getCertificateListPage(@PathVariable("residentSerialNumber") int residentSerialNumber,
                                         Model model) {
        List<CertificateIssueDto> certificateIssueDtolist =
                certificateIssueService.getCertificateIssueDtoListByResidentSerialNumber(residentSerialNumber);

        model.addAttribute("certificateIssues", certificateIssueDtolist);

        return "certificateList";
    }
}
