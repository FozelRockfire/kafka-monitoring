package com.t1study.metricsproducer.api.controller;

import com.example.commonlib.dto.MetricRequest;
import com.t1study.metricsproducer.api.ProducerApi;
import com.t1study.metricsproducer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController implements ProducerApi {

    private final ProducerService producerService;

    @Override
    public ResponseEntity<MetricRequest> sendMetrics(String metricName) {
        return ResponseEntity.ok(producerService.sendMetrics(metricName));
    }

}
