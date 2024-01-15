package com.nhnacademy.edu.springframework.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import org.springframework.stereotype.Component;

@Component
public class JsonDataParser implements DataParser {

    @Override
    public Collection<WaterBill> parse(String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = CsvDataParser.class.getClassLoader().getResource(fileName);

        try {
            return objectMapper.readValue(new File(url.getPath()), new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
