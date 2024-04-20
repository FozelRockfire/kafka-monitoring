package com.example.commonlib.dto;

import lombok.Builder;

@Builder
public record MeasurementDTO(

    String statistic,
    Float value

) {
}
