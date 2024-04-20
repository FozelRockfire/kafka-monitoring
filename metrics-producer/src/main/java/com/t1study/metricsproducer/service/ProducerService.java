package com.t1study.metricsproducer.service;

import com.example.commonlib.dto.MetricDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ProducerService {

    private final KafkaTemplate<Object, Object> template;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ProducerService(KafkaTemplate<Object, Object> template, RestTemplateBuilder restTemplateBuilder,
                           ObjectMapper objectMapper) {
        this.template = template;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    private MetricDTO getMetrics(String metricName) {
        String endpointUrl = "http://localhost:8082/actuator/metrics/" + metricName;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(endpointUrl, String.class);
        MetricDTO metricDTO;

        try {
            metricDTO = objectMapper.readValue(responseEntity.getBody(), MetricDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return metricDTO;
    }

    public MetricDTO sendMetrics(String metricName) {
        MetricDTO metric = getMetrics(metricName);

        log.info("ProducerController send: {}", metric.name());
        template.send("metrics-topic", metric);

        return metric;
    }
}
