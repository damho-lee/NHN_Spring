package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.domain.ResultWaterBill;
import java.util.Collection;

public interface Report {
    void report(Collection<ResultWaterBill> data);
}
