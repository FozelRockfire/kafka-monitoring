package com.example.commonlib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(description = "DTO для измерения")
public record MeasurementDTO(

        @Schema(description = "Статистика", example = "VALUE")
        @NotNull
        String statistic,

        @Schema(description = "Значение", example = "1.11")
        @NotNull
        Float value
) {
}

