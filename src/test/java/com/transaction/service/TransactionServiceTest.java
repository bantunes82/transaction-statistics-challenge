package com.transaction.service;

import com.transaction.model.dto.StatisticsDto;
import com.transaction.model.dto.TransactionDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeNow;
import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeSixtySecondInThePast;
import static java.math.BigDecimal.ROUND_HALF_UP;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @Before
    public void before(){
        transactionService = new TransactionService();

        TransactionDto trans1 = new TransactionDto(getLocalDateTimeNow(),
                new BigDecimal(10).setScale(2,ROUND_HALF_UP));
        TransactionDto trans2= new TransactionDto(getLocalDateTimeNow().minusSeconds(1),
                new BigDecimal(10).setScale(2, ROUND_HALF_UP));
        LocalDateTime localDateTime = getLocalDateTimeNow().minusSeconds(2);
        TransactionDto trans3 = new TransactionDto(localDateTime,
                new BigDecimal(50).setScale(2, ROUND_HALF_UP));
        TransactionDto trans4 = new TransactionDto(localDateTime,
                new BigDecimal(30).setScale(2, ROUND_HALF_UP));
        TransactionDto trans5 = new TransactionDto(getLocalDateTimeNow().minusSeconds(3),
                new BigDecimal(40).setScale(2,ROUND_HALF_UP));
        TransactionDto trans6 = new TransactionDto(getLocalDateTimeSixtySecondInThePast().minusSeconds(1),
                new BigDecimal(100).setScale(2, ROUND_HALF_UP));
        TransactionDto trans7 = new TransactionDto(getLocalDateTimeSixtySecondInThePast().minusSeconds(2),
                new BigDecimal(100).setScale(2, ROUND_HALF_UP));
        TransactionDto trans8 = new TransactionDto(getLocalDateTimeSixtySecondInThePast().minusSeconds(3),
                new BigDecimal(100).setScale(2, ROUND_HALF_UP));

        transactionService.addTransaction(trans1);
        transactionService.addTransaction(trans2);
        transactionService.addTransaction(trans3);
        transactionService.addTransaction(trans4);
        transactionService.addTransaction(trans5);
        transactionService.addTransaction(trans6);
        transactionService.addTransaction(trans7);
        transactionService.addTransaction(trans8);

    }

    @Test
    public void shouldAddTransactions(){
        assertThat(transactionService.getTransactionsSize()).isEqualTo(8);
    }

    @Test
    public void shouldRemoveAllTransactions(){
        transactionService.removeAllTransactions();

        assertThat(transactionService.getTransactionsSize()).isEqualTo(0);
    }

    @Test
    public void shouldRemoveOldTransactions(){
        transactionService.removeOldTransactions();

        assertThat(transactionService.getTransactionsSize()).isEqualTo(5);
    }

    @Test
    public void shouldCalculateStatistics(){
        StatisticsDto statisticsDto = transactionService.calculateStatistics();

        assertThat(statisticsDto.getMax()).isEqualTo("50.00");
        assertThat(statisticsDto.getAvg()).isEqualTo("28.00");
        assertThat(statisticsDto.getMin()).isEqualTo("10.00");
        assertThat(statisticsDto.getSum()).isEqualTo("140.00");
        assertThat(statisticsDto.getCount()).isEqualTo(5);
    }

    @Test
    public void shouldCalculateEmptyStatistics(){
        transactionService.removeAllTransactions();

        StatisticsDto statisticsDto = transactionService.calculateStatistics();

        assertThat(statisticsDto.getMax()).isEqualTo("0.00");
        assertThat(statisticsDto.getAvg()).isEqualTo("0.00");
        assertThat(statisticsDto.getMin()).isEqualTo("0.00");
        assertThat(statisticsDto.getSum()).isEqualTo("0.00");
        assertThat(statisticsDto.getCount()).isEqualTo(0);
    }

}
