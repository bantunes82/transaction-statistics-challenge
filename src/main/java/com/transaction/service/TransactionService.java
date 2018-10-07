package com.transaction.service;

import com.transaction.model.dto.StatisticsDto;
import com.transaction.model.dto.TransactionDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import static com.transaction.util.LocalDateTimeUtils.getLocalDateTimeSixtySecondInThePast;
import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

@Service
public class TransactionService{

    private final Queue<TransactionDto> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(TransactionDto transactionDto){
        transactions.add(transactionDto);
    }

    public void removeAllTransactions(){
        transactions.clear();
    }

    public StatisticsDto calculateStatistics(){
        LocalDateTime sixtySecondsBefore = getLocalDateTimeSixtySecondInThePast();

        List<BigDecimal> amounts = transactions
                                            .stream()
                                            .filter(transactionDto -> transactionDto.getTimestamp().isAfter(sixtySecondsBefore))
                                            .map(TransactionDto::getAmount)
                                            .collect(Collectors.toList());

        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setMax(amounts.stream().max(Comparator.naturalOrder()).orElse(ZERO).setScale(2,ROUND_HALF_UP).toPlainString());
        statisticsDto.setMin(amounts.stream().min(Comparator.naturalOrder()).orElse(ZERO).setScale(2,ROUND_HALF_UP).toPlainString());
        statisticsDto.setCount(amounts.size());
        statisticsDto.setSum(amounts.stream().reduce(ZERO,BigDecimal::add).setScale(2,ROUND_HALF_UP).toPlainString());

        return statisticsDto;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 60000)
    public void removeOldTransactions(){
        LocalDateTime sixtySecondsBefore = getLocalDateTimeSixtySecondInThePast();

        transactions.removeIf(transactionDto -> transactionDto.getTimestamp().isBefore(sixtySecondsBefore));
    }

    int getTransactionsSize(){
        return transactions.size();
    }


}
