package com.alexey.calorietrackerapi.controller;


import com.alexey.calorietrackerapi.exception.UserNotFoundException;
import com.alexey.calorietrackerapi.service.CalorieReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/calorie-tracker/report")
public class CalorieReportController {

    private final CalorieReportService calorieReportService;

    public CalorieReportController(CalorieReportService calorieReportService) {
        this.calorieReportService = calorieReportService;
    }

    @GetMapping("/daily")
    public ResponseEntity<String> getDailyReport(Long userId, LocalDate date) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(calorieReportService.getDailyReport(userId, date).toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/check-goal")
    public ResponseEntity<String> checkDailyGoal(Long userId, LocalDate date) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(calorieReportService.checkDailyGoal(userId, date).toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<String> getMealHistoryPerDay(Long userId, LocalDate startDate, LocalDate endDate) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(calorieReportService.getMealHistoryPerDay(userId, startDate, endDate).toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
