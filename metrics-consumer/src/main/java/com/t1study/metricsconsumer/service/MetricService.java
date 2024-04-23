package com.t1study.metricsconsumer.service;

import com.example.commonlib.dto.MetricRequest;
import com.example.commonlib.dto.MetricResponse;

import java.util.List;

public interface MetricService {

    List<MetricResponse> getAllMetrics();

    MetricResponse getMetricsById(Long id);

    void saveMetric(MetricRequest metricRequest);

}
