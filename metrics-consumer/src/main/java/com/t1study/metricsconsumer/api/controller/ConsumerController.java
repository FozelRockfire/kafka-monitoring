package com.t1study.metricsconsumer.api.controller;

import com.t1study.metricsconsumer.api.ConsumerApi;
import com.t1study.metricsconsumer.model.Metric;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsumerController implements ConsumerApi {

    @Override
    public ResponseEntity<List<Metric>> getAllMetrics() {
        return null;
    }

    @Override
    public ResponseEntity<Metric> getMetricById(Long id) {
        return null;
    }
}
