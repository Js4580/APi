package com.example.demo.services;

import com.example.demo.models.STS;
import com.example.demo.repositories.STSsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class STSsService {
    private final STSsRepository STSsRepository;

    @Autowired
    public STSsService(STSsRepository STSsRepository) {
        this.STSsRepository = STSsRepository;
    }

    @Transactional
    public void registration(STS STS){
        STSsRepository.save(STS);
    }

    public Optional<STS> isPresentClientTinToDB(String name){
        return STSsRepository.findByClientTin(name);
    }
}
