package com.t1study.metricsconsumer.dto;

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

