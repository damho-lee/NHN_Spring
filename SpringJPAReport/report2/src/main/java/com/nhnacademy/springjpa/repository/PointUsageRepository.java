package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.PointUsage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointUsageRepository extends JpaRepository<PointUsage, Integer> {
    List<PointUsage> findPointUsagesByUser_UserId(String userId);
}
