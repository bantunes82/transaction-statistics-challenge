package com.transaction.controller;

import com.transaction.model.dto.TransactionDto;
import com.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions(){
        transactionService.removeAllTransactions();
        return new ResponseEntity<>(NO_CONTENT);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addTransaction(@Valid @RequestBody TransactionDto transactionDto){
        transactionDto.validateRequest();
        transactionService.addTransaction(transactionDto);
        return new ResponseEntity<>(CREATED);
    }


}
