package com.example.una.schoolSchedule.repository;

import com.example.una.schoolSchedule.domain.SchoolSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolScheduleRepository extends JpaRepository<SchoolSchedule, Long> {
    List<SchoolSchedule> findBySdSchulCode(String sdSchulCode);
}
