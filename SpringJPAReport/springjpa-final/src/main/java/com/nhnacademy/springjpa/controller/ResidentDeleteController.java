package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.service.HouseholdCompositionResidentService;
import com.nhnacademy.springjpa.service.HouseholdService;
import com.nhnacademy.springjpa.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residentDelete")
public class ResidentDeleteController {
    private final ResidentService residentService;
    private final HouseholdService householdService;
    private final HouseholdCompositionResidentService householdCompositionResidentService;

    public ResidentDeleteController(ResidentService residentService, HouseholdService householdService,
                                    HouseholdCompositionResidentService householdCompositionResidentService) {
        this.residentService = residentService;
        this.householdService = householdService;
        this.householdCompositionResidentService = householdCompositionResidentService;
    }

    @PostMapping("/{residentSerialNumber}")
    public String deleteResident(@PathVariable("residentSerialNumber") int residentSerialNumber) {
        if (!householdService.isHeadOfHousehold(residentSerialNumber)) {
            residentService.deleteResident(residentSerialNumber);
        } else {
            int householdSerialNumber = householdService.getHouseholdSerialNumberByResidentSerialNumber(residentSerialNumber);
            if (householdCompositionResidentService.isAlone(householdSerialNumber)) {
                residentService.deleteResident(residentSerialNumber);
            }
        }

        return "redirect:/";
    }
}
