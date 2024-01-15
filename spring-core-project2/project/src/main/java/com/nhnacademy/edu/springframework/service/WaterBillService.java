package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.domain.ResultWaterBill;
import java.util.Collection;

public interface WaterBillService {
    Collection<ResultWaterBill> billCalculate(int usage);
}
