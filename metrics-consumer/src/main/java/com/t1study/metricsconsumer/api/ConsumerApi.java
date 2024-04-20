package com.t1study.metricsconsumer.api;

import com.t1study.metricsconsumer.dto.MetricDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/metrics")
public interface ConsumerApi {

    @GetMapping
    ResponseEntity<List<MetricDTO>> getAllMetrics();

    @GetMapping("/{id}")
    ResponseEntity<MetricDTO> getMetricById(
            @PathVariable Long id
    );
}
