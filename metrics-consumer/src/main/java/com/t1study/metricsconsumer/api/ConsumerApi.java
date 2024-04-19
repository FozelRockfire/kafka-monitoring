package com.t1study.metricsconsumer.api;

import com.t1study.metricsconsumer.model.Metric;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/metrics")
public interface ConsumerApi {

    @GetMapping
    ResponseEntity<List<Metric>> getAllMetrics();

    @GetMapping("/{id}")
    ResponseEntity<Metric> getMetricById(
            @PathVariable Long id
    );
}
