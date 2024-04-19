package com.t1study.metricsproducer.api.controller;

import com.t1study.metricsproducer.api.ProducerApi;
import com.t1study.metricsproducer.dto.MetricDTO;
import com.t1study.metricsproducer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProducerController implements ProducerApi {

    private final KafkaTemplate<Object, Object> template;
    private final ProducerService producerService;

    @Override
    public ResponseEntity<String> sendMetrics(String metricName) {
        MetricDTO metric = producerService.getMetrics(metricName);

        log.info("ProducerController: {}", metric.name());
        template.send("metrics-topic", metric);

        return ResponseEntity.ok("Метрика отправлена");
    }

}
