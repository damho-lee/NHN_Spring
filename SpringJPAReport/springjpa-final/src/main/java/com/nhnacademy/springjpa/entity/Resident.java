package com.nhnacademy.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "resident_serial_number")
    private int residentSerialNumber;

    @Column
    private String name;

    @Column(name = "resident_registration_number")
    private String residentRegistrationNumber;

    @Column(name = "gender_code")
    private String gender;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "birth_place_code")
    private String birthPlaceCode;

    @Column(name = "registration_base_address")
    private String registrationBaseAddress;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "death_date")
    private LocalDateTime deathDate;

    @Column(name = "death_place_code")
    private String deathPlaceCode;

    @Column(name = "death_place_address")
    private String deathPlaceAddress;

    @OneToMany(mappedBy = "resident")
    private List<BirthDeathReportResident> birthDeathReportResidents;

    @OneToMany(mappedBy = "reportResident")
    private List<BirthDeathReportResident> birthDeathReportReporterResidents;

    @OneToMany(mappedBy = "resident")
    private List<CertificateIssue> certificateIssues;

    @OneToMany(mappedBy = "resident", cascade = CascadeType.REMOVE)
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "householdResidentSerialNumber", cascade = CascadeType.REMOVE)
    private List<Household> households;

    @OneToMany(mappedBy = "baseResident", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationshipsBase;

    @OneToMany(mappedBy = "familyResident", cascade = CascadeType.REMOVE)
    private List<FamilyRelationship> familyRelationships;
}




















