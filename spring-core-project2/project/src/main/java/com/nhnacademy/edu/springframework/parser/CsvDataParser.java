package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class CsvDataParser implements DataParser {
    @Override
    public Collection<WaterBill> parse(String fileName) {
        Collection<WaterBill> waterBillCollection = new ArrayList<>();

        URL url = CsvDataParser.class.getClassLoader().getResource(fileName);
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(url.getPath()))) {
            while ((line = bufferedReader.readLine()) != null) {
                WaterBill waterBill = makeWaterBill(line);

                if (waterBill != null) {
                    waterBillCollection.add(waterBill);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }

        return waterBillCollection;
    }

    private WaterBill makeWaterBill(String line) {
        String[] strings = line.split(",");

        try {
            int seq = Integer.parseInt(strings[0].trim());
            String city = strings[1].trim();
            String sector = strings[2].trim();
            int level = Integer.parseInt(strings[3].trim());
            int startSection = Integer.parseInt(strings[4].trim());
            int endSection = Integer.parseInt(strings[5].trim());
            int unitPrice = Integer.parseInt(strings[6].trim());
            int basicPrice;
            if (strings.length < 8) {
                basicPrice = 0;
            } else {
                basicPrice = Integer.parseInt(strings[7].trim());
            }

            return new WaterBill(seq, city, sector, level, startSection, endSection, unitPrice, basicPrice);
        } catch (NumberFormatException | IndexOutOfBoundsException exception) {
            return null;
        }
    }
}
