package com.example.commonlib.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
@Schema(description = "DTO для метрики")
public record MetricResponse(

        @Schema(description = "id метрики", example = "1")
        Long id,

        @Schema(description = "Название метрики", example = "disk.total")
        @NotNull
        String name,

        @Schema(description = "Описание метрики", example = "Total space for path")
        String description,

        @Schema(description = "Базовая единица метрики", example = "bytes")
        String baseUnit,

        @Schema(description = "Дата сохранения метрики", example = "2024-04-20T12:00:00Z")
        Date date,

        @Schema(description = "Список измерений")
        List<MeasurementDTO> measurements

) {
}

