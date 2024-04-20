package com.t1study.metricsconsumer.mapper;

import com.example.commonlib.dto.MetricDTO;
import com.t1study.metricsconsumer.model.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MetricMapper extends Mappable<Metric, MetricDTO> {

    MetricMapper INSTANCE = Mappers.getMapper(MetricMapper.class);

    @Override
    default MetricDTO toDTO(Metric metric) {
        return MetricDTO.builder()
                .name(metric.getName().getName())
                .description(metric.getDescription())
                .baseUnit(metric.getBaseUnit() != null ? metric.getBaseUnit().getName() : null)
                .measurements(metric.getMeasurements()
                        .stream()
                        .map(MeasurementMapper.INSTANCE::toDTO)
                        .toList())
                .build();
    }

}
