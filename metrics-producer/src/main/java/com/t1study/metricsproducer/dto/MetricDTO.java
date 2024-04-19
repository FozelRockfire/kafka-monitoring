package com.t1study.metricsproducer.dto;

import lombok.*;

import java.util.List;

@Builder
public record MetricDTO(

    String name,
    String description,
    String baseUnit,
    List<MeasurementDTO> measurements

) {
}

