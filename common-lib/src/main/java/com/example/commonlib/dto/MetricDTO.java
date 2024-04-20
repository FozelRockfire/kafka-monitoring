package com.example.commonlib.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MetricDTO(

    String name,
    String description,
    String baseUnit,
    List<MeasurementDTO> measurements

) {
}

