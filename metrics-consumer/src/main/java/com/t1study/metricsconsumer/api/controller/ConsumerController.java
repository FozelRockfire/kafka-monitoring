package com.t1study.metricsconsumer.api.controller;

import com.example.commonlib.dto.MetricDTO;
import com.t1study.metricsconsumer.api.ConsumerApi;
import com.t1study.metricsconsumer.service.MetricService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsumerController implements ConsumerApi {

    private final MetricService metricService;

    @Override
    public ResponseEntity<List<MetricDTO>> getAllMetrics() {
        return ResponseEntity.ok(metricService.getAllMetrics());
    }

    @Override
    public ResponseEntity<MetricDTO> getMetricById(Long id) {
        return ResponseEntity.ok(metricService.getMetricsById(id));
    }
}
