package com.alexey.calorietrackerapi.controller;


import com.alexey.calorietrackerapi.exception.UserNotFoundException;
import com.alexey.calorietrackerapi.service.CalorieReportService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/calorietracker/report")
public class CalorieReportController {

    private final CalorieReportService calorieReportService;

    public CalorieReportController(CalorieReportService calorieReportService) {
        this.calorieReportService = calorieReportService;
    }

    @GetMapping("/daily")
    public ResponseEntity<String> getDailyReport(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(calorieReportService.getDailyReport(userId, date).toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/checkgoal")
    public ResponseEntity<String> checkDailyGoal(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(calorieReportService.checkDailyGoal(userId, date).toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<String> getMealHistoryPerDay(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam(name = "end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(calorieReportService.getMealHistoryPerDay(userId, startDate, endDate).toString());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
