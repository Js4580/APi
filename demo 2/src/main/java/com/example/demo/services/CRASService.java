package com.example.demo.services;

import com.example.demo.models.CRAS;
import com.example.demo.repositories.CRASRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class CRASService {
    private final CRASRepository CRASRepository;
    private final STSsService STSsService;

    @Autowired
    public CRASService(CRASRepository CRASRepository, STSsService STSsService) {
        this.CRASRepository = CRASRepository;
        this.STSsService = STSsService;
    }

    @Transactional
    public void addMeasurement(CRAS CRAS){
        enrichMeasurement(CRAS);
        CRASRepository.save(CRAS);
    }

    private void enrichMeasurement(CRAS CRAS) {
        CRAS.setPointId(STSsService.isPresentClientTinToDB(CRAS.getPointId().getClientTin()).get());
        CRAS.setTimestamp(LocalDateTime.now());
    }
}
