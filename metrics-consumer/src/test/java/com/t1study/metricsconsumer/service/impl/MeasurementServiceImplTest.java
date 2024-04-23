package com.t1study.metricsconsumer.service.impl;

import com.example.commonlib.dto.MeasurementDTO;
import com.t1study.metricsconsumer.model.Measurement;
import com.t1study.metricsconsumer.model.StatisticName;
import com.t1study.metricsconsumer.repository.MeasurementRepository;
import com.t1study.metricsconsumer.repository.StatisticNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты MeasurementService")
public class MeasurementServiceImplTest {

    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private StatisticNameRepository statisticNameRepository;

    @InjectMocks
    private MeasurementServiceImpl measurementService;

    private MeasurementDTO measurementDTO;
    private Measurement measurement;

    @BeforeEach
    public void setUp() {
        StatisticName statisticName = StatisticName.builder()
                .id(1L)
                .name("STAT")
                .build();

        measurementDTO = new MeasurementDTO("STAT", 1.0f);

        measurement = Measurement.builder()
                .id(1L)
                .statistic(statisticName)
                .value(1.0f)
                .build();
    }

    @Test
    @DisplayName("Проверка метода saveMeasurements при успешном выполнении")
    public void testSaveMeasurements_ValidData_SaveMeasurement() {
        // Arrange
        when(statisticNameRepository.findStatisticNameByName(anyString())).thenReturn(null);
        when(measurementRepository.save(any(Measurement.class))).thenReturn(measurement);

        // Act
        Measurement result = measurementService.saveMeasurements(measurementDTO);

        // Assert
        assertNotNull(result);
        assertEquals(result.getValue(), measurementDTO.value());
        assertNotNull(result.getStatistic());
        assertEquals(result.getStatistic().getName(), measurementDTO.statistic());

        verify(statisticNameRepository).findStatisticNameByName(measurementDTO.statistic());
        verify(measurementRepository).save(any(Measurement.class));
    }

}