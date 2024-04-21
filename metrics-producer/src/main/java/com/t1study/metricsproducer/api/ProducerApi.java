package com.t1study.metricsproducer.api;


import com.example.commonlib.dto.MetricRequest;
import com.example.commonlib.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "API для отправки метрик")
@RequestMapping("/api/metrics")
public interface ProducerApi {

    @Operation(summary = "Отправить метрику", description = "Отправить данные запрошенной метрики")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метрика успешно отправлена",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MetricRequest.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "В случае нарушения контракта",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "В случае внутренних ошибок",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorMessage.class)
                    )
            )
    })
    ResponseEntity<MetricRequest> sendMetrics(
            @Parameter(description = "Имя метрики для отправки", required = true, example = "disk.total")
            @RequestParam(name = "metricName")
            String metricName
    );
}
