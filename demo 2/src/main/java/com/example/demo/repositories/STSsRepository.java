package com.example.demo.repositories;

import com.example.demo.models.STS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface STSsRepository extends JpaRepository<STS, Integer> {
    Optional<STS> findByClientTin(String name);
}
