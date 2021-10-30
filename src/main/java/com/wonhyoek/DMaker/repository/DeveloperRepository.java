package com.wonhyoek.DMaker.repository;

import com.wonhyoek.DMaker.dto.DeveloperDetailDto;
import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.type.StatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeveloperRepository
        extends JpaRepository<Developer, Long> {
    Developer findByMemberId(String memberId);
    List<Developer> findDevelopersByStatusCodeEquals(StatusCode statusCode);
}
