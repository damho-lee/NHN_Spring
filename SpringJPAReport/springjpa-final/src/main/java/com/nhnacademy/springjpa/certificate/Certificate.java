package com.nhnacademy.springjpa.certificate;

public class Certificate {
    private Certificate() {}
    private static long residentRegisterVerificationCode = 9876543200000000L;
    private static long familyRelationshipVerificationCode = 2345678900000000L;
    public static final String RESIDENT_REGISTRATION_CERTIFICATE_TYPE_CODE = "주민등록등본";
    public static final String FAMILY_RELATIONSHIP_CERTIFICATE_TYPE_CODE = "가족관계증명서";
    public static final String BIRTH_CERTIFICATE_TYPE_CODE = "출생신고서";
    public static final String DEATH_CERTIFICATE_TYPE_CODE = "사망신고서";

    public static synchronized long getResidentRegisterVerificationCode() {
        ++residentRegisterVerificationCode;
        if (residentRegisterVerificationCode >= 9876543300000000L) {
            throw new RuntimeException();
        }
        return residentRegisterVerificationCode;
    }

    public static synchronized long getFamilyRelationshipVerificationCode() {
        ++familyRelationshipVerificationCode;
        if (familyRelationshipVerificationCode >= 2345679000000000L) {
            throw new RuntimeException();
        }
        return familyRelationshipVerificationCode;
    }
}
