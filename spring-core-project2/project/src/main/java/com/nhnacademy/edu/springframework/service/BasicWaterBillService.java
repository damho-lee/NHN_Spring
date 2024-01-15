package com.nhnacademy.edu.springframework.service;

import com.nhnacademy.edu.springframework.domain.ResultWaterBill;
import com.nhnacademy.edu.springframework.domain.WaterBill;
import com.nhnacademy.edu.springframework.repository.TariffRepository;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BasicWaterBillService implements WaterBillService {
    private final TariffRepository tariffRepository;

    public BasicWaterBillService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public Collection<ResultWaterBill> billCalculate(int usage) {
        Collection<WaterBill> targetWaterBills = tariffRepository.findWaterBill(usage);
        List<WaterBill> resultWaterBillList = targetWaterBills.stream()
                .sorted(Comparator.comparingInt(
                        waterBill -> (waterBill.getBasicPrice() + waterBill.getUnitPrice() * usage)))
                .limit(5)
                .collect(Collectors.toList());

        return resultWaterBillList.stream()
                .map(waterBill -> new ResultWaterBill(
                        waterBill.getCity(),
                        waterBill.getSector(),
                        waterBill.getUnitPrice(),
                        waterBill.getBasicPrice() + waterBill.getUnitPrice() * usage))
                .collect(Collectors.toList());
    }
}
