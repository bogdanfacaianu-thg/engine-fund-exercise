package com.example.enginefund.api;

import java.math.BigDecimal;

public class ProjectionRequest {

    private BigDecimal currentFundBalance;
    private BigDecimal targetFund;
    private BigDecimal totalPeriodYears;
    private BigDecimal yearsElapsed;
    private BigDecimal hoursFlownSoFar;
    private BigDecimal plannedAverageYearlyHours;
    private BigDecimal maxRealisticYearlyHours;
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

    public BigDecimal getPlannedAverageYearlyHours() {
        return plannedAverageYearlyHours;
    }

    public void setPlannedAverageYearlyHours(BigDecimal plannedAverageYearlyHours) {
        this.plannedAverageYearlyHours = plannedAverageYearlyHours;
    }

    public BigDecimal getMaxRealisticYearlyHours() {
        return maxRealisticYearlyHours;
    }

    public void setMaxRealisticYearlyHours(BigDecimal maxRealisticYearlyHours) {
        this.maxRealisticYearlyHours = maxRealisticYearlyHours;
    }

    public BigDecimal getEngineFundPerHour() {
        return engineFundPerHour;
    }

    public void setEngineFundPerHour(BigDecimal engineFundPerHour) {
        this.engineFundPerHour = engineFundPerHour;
    }
}
