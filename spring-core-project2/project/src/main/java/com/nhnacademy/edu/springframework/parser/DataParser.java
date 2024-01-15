package com.nhnacademy.edu.springframework.parser;

import com.nhnacademy.edu.springframework.domain.WaterBill;
import java.util.Collection;

public interface DataParser {
    Collection<WaterBill> parse(String fileName);
}
