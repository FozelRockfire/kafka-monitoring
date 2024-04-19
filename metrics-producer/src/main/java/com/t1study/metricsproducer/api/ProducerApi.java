package com.t1study.metricsproducer.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/metrics")
public interface ProducerApi {

    @PostMapping
    ResponseEntity<String> sendMetrics(
            @RequestParam String metricName
    );
}
