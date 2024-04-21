package com.t1study.metricsconsumer.service;


import com.example.commonlib.dto.MeasurementDTO;
import com.example.commonlib.dto.MetricRequest;
import com.example.commonlib.dto.MetricResponse;
import com.example.commonlib.exception.NotFoundException;
import com.t1study.metricsconsumer.mapper.MetricMapper;
import com.t1study.metricsconsumer.model.BaseUnit;
import com.t1study.metricsconsumer.model.Measurement;
import com.t1study.metricsconsumer.model.Metric;
import com.t1study.metricsconsumer.model.MetricName;
import com.t1study.metricsconsumer.repository.BaseUnitRepository;
import com.t1study.metricsconsumer.repository.MetricNameRepository;
import com.t1study.metricsconsumer.repository.MetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MetricService {

    private final MeasurementService measurementService;

    private final MetricRepository metricRepository;
    private final MetricNameRepository metricNameRepository;
    private final BaseUnitRepository baseUnitRepository;

    public List<MetricResponse> getAllMetrics() {
        return metricRepository.findAll()
                .stream()
                .map(MetricMapper.INSTANCE::toDTO)
                .toList();
    }

    public MetricResponse getMetricsById(Long id) {
        return MetricMapper.INSTANCE.toDTO(metricRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Метрик с заданным id не существует")));
    }

    public void saveMetric(MetricRequest metricRequest) {
        MetricName metricName = findOrSaveMetricName(metricRequest.name());

        BaseUnit baseUnit = null;
        if (metricRequest.baseUnit() != null) {
            baseUnit = findOrSaveBaseUnit(metricRequest.baseUnit());
        }
        
        Metric metric = metricRepository.save(Metric.builder()
            .name(metricName)
            .description(metricRequest.description())
            .baseUnit(baseUnit)
            .date(new Date())
            .build());

        System.out.println(metric.getId());

        for (MeasurementDTO measurementDTO : metricRequest.measurements()) {
            Measurement measurement = measurementService.saveMeasurements(measurementDTO);
            measurement.setMetric(metric);
        }
    }

    private MetricName findOrSaveMetricName(String name) {
        MetricName metricName = metricNameRepository.findMetricNameByName(name);
        if (metricName == null) {
            metricName = metricNameRepository.save(MetricName.builder()
                    .name(name)
                    .build());
        }
        return metricName;
    }

    private BaseUnit findOrSaveBaseUnit(String baseUnitName) {
        BaseUnit baseUnit = baseUnitRepository.findBaseUnitNameByName(baseUnitName);
        if (baseUnit == null) {
            baseUnit = baseUnitRepository.save(BaseUnit.builder()
                    .name(baseUnitName)
                    .build());
        }
        return baseUnit;
    }
}
