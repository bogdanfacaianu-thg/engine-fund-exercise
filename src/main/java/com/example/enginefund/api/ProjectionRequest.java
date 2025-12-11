package com.example.enginefund.api;

import java.math.BigDecimal;

public class ProjectionRequest {

    private BigDecimal currentFundBalance;
    private BigDecimal targetFund;
    private BigDecimal totalPeriodYears;
    private BigDecimal yearsElapsed;
    private BigDecimal hoursFlownSoFar;
    private BigDecimal targetYearlyHours;
    private BigDecimal engineFundPerHour;

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

    public BigDecimal getTotalPeriodYears() {
        return totalPeriodYears;
    }

    public void setTotalPeriodYears(BigDecimal totalPeriodYears) {
        this.totalPeriodYears = totalPeriodYears;
    }

    public BigDecimal getYearsElapsed() {
        return yearsElapsed;
    }

    public void setYearsElapsed(BigDecimal yearsElapsed) {
        this.yearsElapsed = yearsElapsed;
    }

    public BigDecimal getHoursFlownSoFar() {
        return hoursFlownSoFar;
    }

    public void setHoursFlownSoFar(BigDecimal hoursFlownSoFar) {
        this.hoursFlownSoFar = hoursFlownSoFar;
    }

    public BigDecimal getTargetYearlyHours() {
        return targetYearlyHours;
    }

    public void setTargetYearlyHours(BigDecimal targetYearlyHours) {
        this.targetYearlyHours = targetYearlyHours;
    }

    public BigDecimal getEngineFundPerHour() {
        return engineFundPerHour;
    }

    public void setEngineFundPerHour(BigDecimal engineFundPerHour) {
        this.engineFundPerHour = engineFundPerHour;
    }
}
