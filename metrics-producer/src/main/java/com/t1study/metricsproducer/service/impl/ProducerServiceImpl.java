package com.t1study.metricsproducer.service.impl;

import com.example.commonlib.dto.MetricRequest;
import com.example.commonlib.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.t1study.metricsproducer.model.MainMetrics;
import com.t1study.metricsproducer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final KafkaTemplate<Object, Object> template;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public MetricRequest sendMetrics(String metricName) {
        MetricRequest metric = getMetrics(metricName);

        log.info("ProducerController send: {}", metric.name());
        template.send("metrics-topic", metric);

        return metric;
    }

    private MetricRequest getMetrics(String metricName) {
        String endpointUrl = "http://localhost:8082/actuator/metrics/" + metricName;

        ResponseEntity<String> responseEntity;
        try {
             responseEntity = restTemplate.getForEntity(endpointUrl, String.class);
        } catch (HttpClientErrorException e){
            throw new NotFoundException("Метрик с заданным именем не существует, " +
                    "для просмотра доступных метрик используйте /actuator/metrics");
        }
        MetricRequest metricRequest;

        try {
            metricRequest = objectMapper.readValue(responseEntity.getBody(), MetricRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }

        return metricRequest;
    }

    @Scheduled(fixedRate = 5000L)
    private void sendRandomMainMetric() {
        Random random = new Random();
        MainMetrics[] metrics = MainMetrics.values();

        MainMetrics randomMetric = metrics[random.nextInt(metrics.length)];
        sendMetrics(randomMetric.getAddress());
    }

}
