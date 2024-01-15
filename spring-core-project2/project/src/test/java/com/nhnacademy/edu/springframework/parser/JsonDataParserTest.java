package com.nhnacademy.edu.springframework.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonDataParserTest {
    private DataParser dataParser;
    private String fileName;

    @BeforeEach
    void setup() {
        dataParser = new JsonDataParser();
        fileName = "data/Tariff_Test.json";
    }

    @Test
    void parseTest() {
        Collection<WaterBill> waterBillCollection = dataParser.parse(fileName);

        assertNotNull(waterBillCollection);
        assertEquals(10, waterBillCollection.size());
    }
}
