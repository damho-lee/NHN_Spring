package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.Collection;

public interface TariffRepository {
    void load(String path);
    Collection<WaterBill> findWaterBill(int usage);
}
