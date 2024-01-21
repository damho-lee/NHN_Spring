package com.nhnacademy.springjpa.domain;

import java.time.LocalDateTime;

public interface CertificateIssueDto {
    String getCertificateTypeCode();
    LocalDateTime getCertificateIssueDate();
}
