package com.nhnacademy.springjpa.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "household")
public class Household { // 세대
    @Id
    @Column(name = "household_serial_number")
    private int householdSerialNumber;

    @ManyToOne
    @JoinColumn(name = "household_resident_serial_number")
    private Resident householdResidentSerialNumber;

    @Column(name = "household_composition_date")
    private LocalDateTime householdCompositionDate;

    @Column(name = "household_composition_reason_code")
    private String householdCompositionReasonCode;

    @Column(name = "current_house_movement_address")
    private String currentHouseMovementAddress;

    @OneToMany(mappedBy = "household", cascade = {CascadeType.REMOVE})
    private List<HouseholdCompositionResident> householdCompositionResidents;

    @OneToMany(mappedBy = "household", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<HouseholdMovementAddress> householdMovementAddresses;
}
