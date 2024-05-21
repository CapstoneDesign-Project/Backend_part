package com.example.una.schoolSchedule.controller;

import com.example.una.schoolSchedule.domain.SchoolSchedule;
import com.example.una.schoolSchedule.service.SchoolScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class SchoolScheduleController {

    private final SchoolScheduleService schoolScheduleService;

    @Autowired
    public SchoolScheduleController(SchoolScheduleService schoolScheduleService){
        this.schoolScheduleService = schoolScheduleService;
    }

    @GetMapping("/resultSchoolSchedule/{sdSchulCode}")
    public List<SchoolSchedule> ResultSchoolSchedule(@PathVariable String sdSchulCode){
        System.out.println("학사일정 조회 성공");
        return schoolScheduleService.getAllSchoolSchedules(sdSchulCode);
    }

    @PostMapping("/getSchoolSchedule")
    public List<SchoolSchedule> getSchoolSchedule(@RequestBody Map<String, String> requestBody) {
        String sdSchulCode = requestBody.get("SD_SCHUL_CODE");
        List<SchoolSchedule> schedules = schoolScheduleService.getAllSchoolSchedules(sdSchulCode);

        if (schedules.isEmpty()) {
            schoolScheduleService.fetchAndSaveSchoolSchedule(sdSchulCode);
            schedules = schoolScheduleService.getAllSchoolSchedules(sdSchulCode);
        }
        return schedules;
    }

}
