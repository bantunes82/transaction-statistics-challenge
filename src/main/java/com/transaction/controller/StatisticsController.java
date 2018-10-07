package com.transaction.controller;

import com.transaction.model.dto.StatisticsDto;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "/statistics")
public class StatisticsController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDto> returnStatistics(){
        return ResponseEntity.ok(transactionService.calculateStatistics());
    }
}
