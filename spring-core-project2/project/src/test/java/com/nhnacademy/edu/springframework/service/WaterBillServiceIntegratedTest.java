package com.nhnacademy.edu.springframework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.edu.springframework.config.JavaConfig;
import com.nhnacademy.edu.springframework.domain.ResultWaterBill;
import com.nhnacademy.edu.springframework.repository.TariffRepository;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class WaterBillServiceIntegratedTest {

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private WaterBillService waterBillService;

    @BeforeEach
    void setup() {
        tariffRepository.load("data/Tariff_Test.csv");
    }

    @Test
    void billCalculateTest() {
        Collection<ResultWaterBill> waterBillCollection = waterBillService.billCalculate(1);

        assertEquals(4, waterBillCollection.size());
    }
}
