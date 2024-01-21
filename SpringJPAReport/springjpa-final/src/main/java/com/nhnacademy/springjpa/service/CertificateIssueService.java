package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.domain.CertificateIssueDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.repository.CertificateIssueRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CertificateIssueService {
    private final CertificateIssueRepository certificateIssueRepository;

    public CertificateIssueService(CertificateIssueRepository certificateIssueRepository) {
        this.certificateIssueRepository = certificateIssueRepository;
    }

    public void registerCertificateIssue(CertificateIssue certificateIssue) {
        certificateIssueRepository.save(certificateIssue);
    }

    public List<CertificateIssueDto> getCertificateIssueDtoListByResidentSerialNumber(int residentSerialNumber) {
        return certificateIssueRepository.findByResident_ResidentSerialNumber(residentSerialNumber);
    }

    public CertificateIssueDto getCertificateIssueDtoByCertificateConfirmationNumber(
            long certificateConfirmationNumber) {
        return certificateIssueRepository.findByCertificateConfirmationNumber(certificateConfirmationNumber);
    }
}
