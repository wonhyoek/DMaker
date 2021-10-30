package com.wonhyoek.DMaker.repository;

import com.wonhyoek.DMaker.entity.Developer;
import com.wonhyoek.DMaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetiredDeveloperRepository
        extends JpaRepository<RetiredDeveloper, Long> {
    Developer findByMemberId(String memberId);
}
