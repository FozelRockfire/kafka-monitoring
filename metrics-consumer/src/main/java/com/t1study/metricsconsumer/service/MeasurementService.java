package com.t1study.metricsconsumer.service;

import com.example.commonlib.dto.MeasurementDTO;
import com.t1study.metricsconsumer.model.Measurement;

public interface MeasurementService {

    Measurement saveMeasurements(MeasurementDTO measurementDTO);
}
