package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.CertificateIssueDto;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateIssueRepository extends JpaRepository<CertificateIssue, Long> {
    List<CertificateIssueDto> findByResident_ResidentSerialNumber(int residentSerialNumber);
    CertificateIssueDto findByCertificateConfirmationNumber(long certificateConfirmationNumber);
}
