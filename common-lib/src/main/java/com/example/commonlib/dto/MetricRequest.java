package com.example.commonlib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(description = "DTO для метрики")
public record MetricRequest(

        @Schema(description = "Название метрики", example = "disk.total")
        @NotNull
        String name,

        @Schema(description = "Описание метрики", example = "Total space for path")
        String description,

        @Schema(description = "Базовая единица метрики", example = "bytes")
        String baseUnit,

        @Schema(description = "Список измерений")
        List<MeasurementDTO> measurements

) {
}

