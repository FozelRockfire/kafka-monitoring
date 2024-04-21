package com.t1study.metricsproducer.service;

import com.example.commonlib.dto.MetricRequest;

public interface ProducerService {

    MetricRequest sendMetrics(String metricName);
}

