package com.nhnacademy.edu.springframework.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.nhnacademy.edu.springframework.config.JavaConfig;
import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class TariffRepositoryIntegratedTest {

    @Autowired
    private TariffRepository tariffRepository;

    @BeforeEach
    void setup() {
        tariffRepository.load("data/Tariff_Test.csv");
    }

    @Test
    void findWaterBillTest() {
        Collection<WaterBill> waterBillCollection = tariffRepository.findWaterBill(10);

        assertEquals(4, waterBillCollection.size());
    }
}
