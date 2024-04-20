package com.t1study.metricsconsumer.mapper;

import com.example.commonlib.dto.MeasurementDTO;
import com.t1study.metricsconsumer.model.Measurement;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MeasurementMapper extends Mappable<Measurement, MeasurementDTO> {

    MeasurementMapper INSTANCE = Mappers.getMapper(MeasurementMapper.class);

    @Override
    default MeasurementDTO toDTO(Measurement measurement) {
        return MeasurementDTO.builder()
                .statistic(measurement.getStatistic().getName())
                .value(measurement.getValue())
                .build();
    }
}
