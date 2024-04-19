package com.t1study.metricsproducer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1study.metricsproducer.dto.MetricDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProducerService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public ProducerService(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public MetricDTO getMetrics(String metricName) {
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

}
