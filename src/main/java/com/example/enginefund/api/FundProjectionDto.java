package com.example.enginefund.api;

import java.math.BigDecimal;

public class FundProjectionDto {

    private BigDecimal currentFundBalance;
    private BigDecimal targetFund;
    private BigDecimal yearsRemaining;
    private BigDecimal remainingHours;
    private BigDecimal engineFundPerHourCurrent;
    private BigDecimal finalProjectedBalance;
    private BigDecimal differenceAtEnd;
    private BigDecimal suggestedCashCall;
     private BigDecimal suggestedYearlyCashCall;
    private BigDecimal suggestedEngineFundPerHour;

    public BigDecimal getCurrentFundBalance() {
        return currentFundBalance;
    }

    public void setCurrentFundBalance(BigDecimal currentFundBalance) {
        this.currentFundBalance = currentFundBalance;
    }

    public BigDecimal getTargetFund() {
        return targetFund;
    }

    public void setTargetFund(BigDecimal targetFund) {
        this.targetFund = targetFund;
    }

    public BigDecimal getYearsRemaining() {
        return yearsRemaining;
    }

    public void setYearsRemaining(BigDecimal yearsRemaining) {
        this.yearsRemaining = yearsRemaining;
    }

    public BigDecimal getRemainingHours() {
        return remainingHours;
    }

    public void setRemainingHours(BigDecimal remainingHours) {
        this.remainingHours = remainingHours;
    }

    public BigDecimal getEngineFundPerHourCurrent() {
        return engineFundPerHourCurrent;
    }

    public void setEngineFundPerHourCurrent(BigDecimal engineFundPerHourCurrent) {
        this.engineFundPerHourCurrent = engineFundPerHourCurrent;
    }

    public BigDecimal getFinalProjectedBalance() {
        return finalProjectedBalance;
    }

    public void setFinalProjectedBalance(BigDecimal finalProjectedBalance) {
        this.finalProjectedBalance = finalProjectedBalance;
    }

    public BigDecimal getDifferenceAtEnd() {
        return differenceAtEnd;
    }

    public void setDifferenceAtEnd(BigDecimal differenceAtEnd) {
        this.differenceAtEnd = differenceAtEnd;
    }

    public BigDecimal getSuggestedCashCall() {
        return suggestedCashCall;
    }

    public void setSuggestedCashCall(BigDecimal suggestedCashCall) {
        this.suggestedCashCall = suggestedCashCall;
    }

    public BigDecimal getSuggestedYearlyCashCall() {
        return suggestedYearlyCashCall;
    }

    public void setSuggestedYearlyCashCall(BigDecimal suggestedYearlyCashCall) {
        this.suggestedYearlyCashCall = suggestedYearlyCashCall;
    }

    public BigDecimal getSuggestedEngineFundPerHour() {
        return suggestedEngineFundPerHour;
    }

    public void setSuggestedEngineFundPerHour(BigDecimal suggestedEngineFundPerHour) {
        this.suggestedEngineFundPerHour = suggestedEngineFundPerHour;
    }
}
