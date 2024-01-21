package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.domain.ResidentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResidentRepositoryCustom {
    Page<ResidentDto> findResidentDtoForIndexPage(Pageable pageable);
}
