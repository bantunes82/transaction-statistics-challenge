package com.transaction.exception;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;


public class FutureTimeStampException extends RuntimeException {
    public FutureTimeStampException(LocalDateTime timestamp) {
        super(String.format("The TimeStamp %s is in the future",timestamp.format(ISO_DATE_TIME)));
    }
}
