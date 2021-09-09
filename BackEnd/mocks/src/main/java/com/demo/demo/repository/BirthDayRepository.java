package com.demo.demo.repository;

import com.demo.demo.model.BirthDayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthDayRepository extends JpaRepository<BirthDayConfig, Long> {
}
