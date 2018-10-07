package com.transaction.controller;

import com.transaction.exception.FutureTimeStampException;
import com.transaction.exception.OldTimeStampException;
import com.transaction.model.dto.TransactionDto;
import com.transaction.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeNow;
import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeSixtySecondInThePast;
import static java.math.BigDecimal.ROUND_HALF_UP;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    public void shouldDeleteTransactions(){
        assertThat(transactionController.deleteTransactions().getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    public void shouldAddTransaction(){
        TransactionDto transactionDto = new TransactionDto(getLocalDateTimeNow(),
                new BigDecimal(10).setScale(2,ROUND_HALF_UP));

        assertThat(transactionController.addTransaction(transactionDto).getStatusCode()).isEqualTo(CREATED);
    }

    @Test(expected = OldTimeStampException.class)
    public void shouldNotAddTransactionTimeStampIsOld(){
        TransactionDto transactionDto = new TransactionDto(getLocalDateTimeSixtySecondInThePast(),
                new BigDecimal(10).setScale(2,ROUND_HALF_UP));

        transactionController.addTransaction(transactionDto);
    }

    @Test(expected = FutureTimeStampException.class)
    public void shouldNotAddTransactionTimeStampIsFuture(){
        TransactionDto transactionDto = new TransactionDto(getLocalDateTimeNow().plusSeconds(1),
                new BigDecimal(10).setScale(2,ROUND_HALF_UP));

        transactionController.addTransaction(transactionDto);
    }
}
