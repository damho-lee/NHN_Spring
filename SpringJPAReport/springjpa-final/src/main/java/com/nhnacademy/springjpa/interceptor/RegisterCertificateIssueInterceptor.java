package com.nhnacademy.springjpa.interceptor;

import static com.nhnacademy.springjpa.certificate.Certificate.BIRTH_CERTIFICATE_TYPE_CODE;
import static com.nhnacademy.springjpa.certificate.Certificate.DEATH_CERTIFICATE_TYPE_CODE;
import static com.nhnacademy.springjpa.certificate.Certificate.FAMILY_RELATIONSHIP_CERTIFICATE_TYPE_CODE;
import static com.nhnacademy.springjpa.certificate.Certificate.RESIDENT_REGISTRATION_CERTIFICATE_TYPE_CODE;

import com.nhnacademy.springjpa.certificate.Certificate;
import com.nhnacademy.springjpa.entity.CertificateIssue;
import com.nhnacademy.springjpa.entity.Resident;
import com.nhnacademy.springjpa.service.CertificateIssueService;
import com.nhnacademy.springjpa.service.ResidentService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RegisterCertificateIssueInterceptor implements HandlerInterceptor {
    private final CertificateIssueService certificateIssueService;
    private final ResidentService residentService;
    private static final String LOCALHOST_PREFIX = "http://localhost:8080";

    public RegisterCertificateIssueInterceptor(CertificateIssueService certificateIssueService,
                                               ResidentService residentService) {
        this.certificateIssueService = certificateIssueService;
        this.residentService = residentService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURL().toString();
        int residentSerialNumber = Integer.parseInt(url.substring(url.lastIndexOf("/") + 1));
        log.error("residentSerialNumber : {}", residentSerialNumber);
        LocalDateTime now = LocalDateTime.now();
        Resident resident = residentService.getResidentByResidentSerialNumber(residentSerialNumber);
        CertificateIssue certificateIssue = new CertificateIssue();
        certificateIssue.setCertificateConfirmationNumber(Certificate.getResidentRegisterVerificationCode());
        certificateIssue.setResident(resident);

        log.error("url : {}", url);

        if (url.startsWith(LOCALHOST_PREFIX + "/residentRegister")) {
            certificateIssue.setCertificateTypeCode(RESIDENT_REGISTRATION_CERTIFICATE_TYPE_CODE);
        } else if(url.startsWith(LOCALHOST_PREFIX + "/familyRelationship")) {
            certificateIssue.setCertificateTypeCode(FAMILY_RELATIONSHIP_CERTIFICATE_TYPE_CODE);
        } else if(url.startsWith(LOCALHOST_PREFIX + "/birthReport")) {
            certificateIssue.setCertificateTypeCode(BIRTH_CERTIFICATE_TYPE_CODE);
        } else if(url.startsWith(LOCALHOST_PREFIX + "/deathReport")) {
            certificateIssue.setCertificateTypeCode(DEATH_CERTIFICATE_TYPE_CODE);
        } else {
            throw new RuntimeException();
        }

        certificateIssue.setCertificateIssueDate(now);

        request.setAttribute("certificateConfirmationNumber", certificateIssue.getCertificateConfirmationNumber());

        certificateIssueService.registerCertificateIssue(certificateIssue);

        return true;
    }
}
