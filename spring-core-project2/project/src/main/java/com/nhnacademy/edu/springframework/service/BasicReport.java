package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.domain.ResultWaterBill;
import java.util.Collection;
import org.springframework.stereotype.Service;

@Service
public class BasicReport implements Report {
    @Override
    public void report(Collection<ResultWaterBill> data) {
        if (data.isEmpty()) {
            System.out.println("검색 결과 없음.");
        }

        for (ResultWaterBill resultWaterBill : data) {
            System.out.println(
                    "WaterBill{city='" + resultWaterBill.getCity() + "', sector='" + resultWaterBill.getSector() +
                            "', unitPrice=" + resultWaterBill.getUnitPrice() + ", billTotal=" + resultWaterBill.getBillTotal() + "}");
        }

    }
}
