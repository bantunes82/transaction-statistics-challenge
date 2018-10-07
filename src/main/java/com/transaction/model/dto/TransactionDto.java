package com.transaction.model.dto;

import com.transaction.exception.FutureTimeStampException;
import com.transaction.exception.OldTimeStampException;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeNow;
import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeSixtySecondInThePast;

public class TransactionDto{

    @NotNull
    private LocalDateTime timestamp;

    @NotNull
    private BigDecimal amount;

    public TransactionDto(LocalDateTime timestamp, BigDecimal amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void validateRequest(){
        if(timestamp.isBefore(getLocalDateTimeSixtySecondInThePast())){
            throw new OldTimeStampException(timestamp);
        }else if (timestamp.isAfter(getLocalDateTimeNow())){
            throw new FutureTimeStampException(timestamp);
        }
    }
}
