package com.transaction.exception;

import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_DATE_TIME;


public class OldTimeStampException extends RuntimeException {
    public OldTimeStampException(LocalDateTime timestamp) {
        super(String.format("The TimeStamp %s is older than 60 seconds",timestamp.format(ISO_DATE_TIME)));
    }
}
