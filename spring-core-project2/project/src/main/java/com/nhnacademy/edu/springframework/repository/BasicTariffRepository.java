package com.nhnacademy.edu.springframework.repository;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import com.nhnacademy.edu.springframework.parser.DataParser;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BasicTariffRepository implements TariffRepository {
    private final DataParser csvDataParser;
    private final DataParser jsonDataParser;
    private Collection<WaterBill> waterBills;

    public BasicTariffRepository(@Qualifier("csvDataParser") DataParser csvDataParser,
                                 @Qualifier("jsonDataParser") DataParser jsonDataParser) {
        this.csvDataParser = csvDataParser;
        this.jsonDataParser = jsonDataParser;
    }

    @Override
    public void load(String path) {
        if (path.trim().contains(".csv")) {
            this.waterBills = csvDataParser.parse(path);
        } else if (path.trim().contains(".json")){
            this.waterBills = jsonDataParser.parse(path);
        } else {
            throw new IllegalArgumentException("path is invalid");
        }
    }

    @Override
    public Collection<WaterBill> findWaterBill(int usage) {
        return waterBills.stream()
                .filter(x -> x.getStartSection() <= usage && x.getEndSection() >= usage)
                .collect(Collectors.toList());
    }
}
