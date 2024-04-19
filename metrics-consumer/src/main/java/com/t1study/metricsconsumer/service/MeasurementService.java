package com.t1study.metricsconsumer.service;

import com.t1study.metricsconsumer.dto.MeasurementDTO;
import com.t1study.metricsconsumer.model.Measurement;
import com.t1study.metricsconsumer.model.StatisticName;
import com.t1study.metricsconsumer.repository.MeasurementRepository;
import com.t1study.metricsconsumer.repository.StatisticNameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final StatisticNameRepository statisticNameRepository;

    public Measurement saveMeasurements(MeasurementDTO measurement){
        StatisticName statisticName = findOrSaveStatisticName(measurement.statistic());

        return measurementRepository.save(Measurement.builder()
                        .statistic(statisticName)
                        .value(measurement.value())
                .build());
    }

    private StatisticName findOrSaveStatisticName(String name) {
        StatisticName statisticName = statisticNameRepository.findStatisticNameByName(name);
        if (statisticName == null) {
            statisticName = statisticNameRepository.save(StatisticName.builder()
                    .name(name)
                    .build());
        }
        return statisticName;
    }
}
