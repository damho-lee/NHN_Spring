package com.nhnacademy.springjpa.controller;

import com.nhnacademy.springjpa.domain.ResidentDto;
import com.nhnacademy.springjpa.service.ResidentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class IndexController {
    private final ResidentService residentService;

    public IndexController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public String getIndexPage(Model model, @PageableDefault(size = 3) Pageable pageable) {
        Page<ResidentDto> residentDtoPage = residentService.getResidents(pageable);

        model.addAttribute("residents", residentDtoPage.getContent());
        model.addAttribute("endPage", residentDtoPage.getTotalPages());

        return "index";
    }
}
