package com.transaction.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.transaction.exception.FutureTimeStampException;
import com.transaction.exception.OldTimeStampException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobaExceptionHandlerlController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({MethodArgumentNotValidException.class,JsonParseException.class})
    public ResponseEntity<Void> invalidJson(Exception ex){
        log.error(ex.getMessage(),ex);

       return new ResponseEntity<>(BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class,FutureTimeStampException.class})
    public ResponseEntity<Void> invalidRequestValues(Exception ex){
        log.error(ex.getMessage(),ex);

        return new ResponseEntity<>(UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(OldTimeStampException.class)
    public ResponseEntity<Void> oldTimeStamp(OldTimeStampException ex){
        log.error(ex.getMessage(),ex);

        return new ResponseEntity<>(NO_CONTENT);
    }

}
