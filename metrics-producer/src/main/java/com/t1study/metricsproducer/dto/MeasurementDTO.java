package com.t1study.metricsproducer.dto;

import lombok.*;

@Builder
public record MeasurementDTO(

    String statistic,
    Float value

) {
}
