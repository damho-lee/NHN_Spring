package com.nhnacademy.edu.springframework.repository;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nhnacademy.edu.springframework.parser.DataParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TariffRepositoryUnitTest {
    @Mock
    private DataParser mockDataParser;

    @InjectMocks
    private BasicTariffRepository mockTariffRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadTest() {
        when(mockDataParser.parse(anyString())).thenReturn(null);
        mockTariffRepository.load("test");
        verify(mockDataParser, times(1)).parse("test");
    }
}
