package com.t1study.metricsconsumer.dto;

import lombok.Builder;

@Builder
public record MeasurementDTO(

    String statistic,
    Float value

) {
}
