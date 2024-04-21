package com.t1study.metricsproducer.api.controller;

import com.example.commonlib.dto.MetricRequest;
import com.t1study.metricsproducer.service.ProducerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
@DisplayName("Тесты ProducerController")
public class ProducerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducerService producerService;

    private final MetricRequest metricRequest = MetricRequest.builder()
            .name("имя метрики")
            .description("описание метрики")
            .baseUnit("единицы измерения")
            .measurements(null)
            .build();

    @Test
    @DisplayName("Проверка метода sendMetrics() при успешном выполнении")
    public void testSendMetrics() throws Exception {
        when(producerService.sendMetrics("эндпоинт метрики")).thenReturn(metricRequest);

        mockMvc.perform(post("/api/metrics")
                        .param("metricName", "эндпоинт метрики")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("имя метрики"))
                .andExpect(jsonPath("$.description").value("описание метрики"))
                .andExpect(jsonPath("$.baseUnit").value("единицы измерения"));
    }
}