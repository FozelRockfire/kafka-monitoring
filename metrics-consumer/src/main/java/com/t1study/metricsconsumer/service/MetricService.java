package com.t1study.metricsconsumer.service;

import com.t1study.metricsconsumer.dto.MeasurementDTO;
import com.t1study.metricsconsumer.dto.MetricDTO;
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

@Service
@RequiredArgsConstructor
@Transactional
public class MetricService {

    private final MeasurementService measurementService;

    private final MetricRepository metricRepository;
    private final MetricNameRepository metricNameRepository;
    private final BaseUnitRepository baseUnitRepository;

    public void saveMetric(MetricDTO metricDTO) {
        MetricName metricName = findOrSaveMetricName(metricDTO.name());

        BaseUnit baseUnit = null;
        if (metricDTO.baseUnit() != null) {
            baseUnit = findOrSaveBaseUnit(metricDTO.baseUnit());
        }
        
        Metric metric = metricRepository.save(Metric.builder()
            .name(metricName)
            .description(metricDTO.description())
            .baseUnit(baseUnit)
            .date(new Date())
            .build());

        System.out.println(metric.getId());

        for (MeasurementDTO measurementDTO : metricDTO.measurements()) {
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
