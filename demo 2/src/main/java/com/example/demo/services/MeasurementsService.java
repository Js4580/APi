package com.example.demo.services;

import com.example.demo.models.Measurement;
import com.example.demo.repositories.MeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;
    private final STSsService STSsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, STSsService STSsService) {
        this.measurementsRepository = measurementsRepository;
        this.STSsService = STSsService;
    }

    public List<Measurement> allMeasurements(){
        return measurementsRepository.findAll();
    }

    @Transactional
    public void addMeasurement(Measurement measurement){
        enrichMeasurement(measurement);
        measurementsRepository.save(measurement);
    }

    private void enrichMeasurement(Measurement measurement) {
        measurement.setPointId(STSsService.isPresentClientTinToDB(measurement.getPointId().getClientTin()).get());
        measurement.setTimestamp(LocalDateTime.now());
    }
}
