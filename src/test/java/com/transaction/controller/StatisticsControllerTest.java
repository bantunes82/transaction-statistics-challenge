package com.transaction.controller;

import com.transaction.model.dto.StatisticsDto;
import com.transaction.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private StatisticsController statisticsController;

    @Test
    public void shouldReturnStatistics(){
        StatisticsDto statisticsDtoMock = new StatisticsDto();
        statisticsDtoMock.setCount(3);
        statisticsDtoMock.setMax("120.00");
        statisticsDtoMock.setMin("30.34");
        statisticsDtoMock.setSum("180.45");

        when(transactionService.calculateStatistics()).thenReturn(statisticsDtoMock);

        ResponseEntity<StatisticsDto> statisticsDto = statisticsController.returnStatistics();

        assertThat(statisticsDto.getBody().getAvg()).isEqualTo("60.15");
        assertThat(statisticsDto.getBody().getCount()).isEqualTo(3);
        assertThat(statisticsDto.getBody().getMax()).isEqualTo("120.00");
        assertThat(statisticsDto.getBody().getMin()).isEqualTo("30.34");
        assertThat(statisticsDto.getBody().getSum()).isEqualTo("180.45");
        assertThat(statisticsDto.getStatusCode()).isEqualTo(OK);
    }

    @Test
    public void shouldReturnEmptyStatistics(){
        StatisticsDto statisticsDtoMock = new StatisticsDto();
        when(transactionService.calculateStatistics()).thenReturn(statisticsDtoMock);

        ResponseEntity<StatisticsDto> statisticsDto = statisticsController.returnStatistics();

        assertThat(statisticsDto.getBody().getAvg()).isEqualTo("0.00");
        assertThat(statisticsDto.getBody().getCount()).isEqualTo(0);
        assertThat(statisticsDto.getBody().getMax()).isEqualTo("0.00");
        assertThat(statisticsDto.getBody().getMin()).isEqualTo("0.00");
        assertThat(statisticsDto.getBody().getSum()).isEqualTo("0.00");
        assertThat(statisticsDto.getStatusCode()).isEqualTo(OK);
    }
}
