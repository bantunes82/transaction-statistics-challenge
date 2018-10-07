package com.transaction.model.dto;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static java.math.BigDecimal.ZERO;

public class StatisticsDto {

    private String sum;
    private String max;
    private String min;
    private long count;

    public StatisticsDto() {
        this.sum = ZERO.setScale(2,ROUND_HALF_UP).toPlainString();
        this.max = ZERO.setScale(2,ROUND_HALF_UP).toPlainString();
        this.min = ZERO.setScale(2,ROUND_HALF_UP).toPlainString();
    }

    public String getAvg() {
        if(this.getSum().equals(ZERO.toPlainString()) || this.getCount() == 0){
            return ZERO.setScale(2,ROUND_HALF_UP).toPlainString();
        }else{
            return new BigDecimal(this.getSum()).divide(new BigDecimal(this.getCount()),2,ROUND_HALF_UP).toPlainString();
        }
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
