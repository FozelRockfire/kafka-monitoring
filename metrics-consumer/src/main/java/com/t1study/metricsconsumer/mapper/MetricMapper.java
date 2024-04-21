package com.t1study.metricsconsumer.mapper;

import com.example.commonlib.dto.MetricResponse;
import com.t1study.metricsconsumer.model.Metric;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MetricMapper extends Mappable<Metric, MetricResponse> {

    MetricMapper INSTANCE = Mappers.getMapper(MetricMapper.class);

    @Override
    default MetricResponse toDTO(Metric metric) {
        return MetricResponse.builder()
                .id(metric.getId())
                .name(metric.getName().getName())
                .description(metric.getDescription())
                .baseUnit(metric.getBaseUnit() != null ? metric.getBaseUnit().getName() : null)
                .date(metric.getDate())
                .measurements(metric.getMeasurements()
                        .stream()
                        .map(MeasurementMapper.INSTANCE::toDTO)
                        .toList())
                .build();
    }

}
