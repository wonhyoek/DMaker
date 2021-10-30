package com.wonhyoek.DMaker.repository;

import com.wonhyoek.DMaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository
        extends JpaRepository<Developer, Long> {
}
