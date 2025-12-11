package com.example.enginefund.api;

import java.math.BigDecimal;

public class HoursSummaryDto {

    private BigDecimal totalPeriodYears;
    private BigDecimal yearsElapsed;
    private BigDecimal yearsRemaining;
    private BigDecimal hoursFlownSoFar;
    private BigDecimal expectedHoursSoFar;
    private BigDecimal differenceHours;
    private BigDecimal actualAverageYearlyHoursSoFar;
    private BigDecimal requiredAverageYearlyHoursRemaining;
    private BigDecimal targetYearlyHours;
    private boolean hoursPlanRealistic;

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

    public BigDecimal getYearsRemaining() {
        return yearsRemaining;
    }

    public void setYearsRemaining(BigDecimal yearsRemaining) {
        this.yearsRemaining = yearsRemaining;
    }

    public BigDecimal getHoursFlownSoFar() {
        return hoursFlownSoFar;
    }

    public void setHoursFlownSoFar(BigDecimal hoursFlownSoFar) {
        this.hoursFlownSoFar = hoursFlownSoFar;
    }

    public BigDecimal getExpectedHoursSoFar() {
        return expectedHoursSoFar;
    }

    public void setExpectedHoursSoFar(BigDecimal expectedHoursSoFar) {
        this.expectedHoursSoFar = expectedHoursSoFar;
    }

    public BigDecimal getDifferenceHours() {
        return differenceHours;
    }

    public void setDifferenceHours(BigDecimal differenceHours) {
        this.differenceHours = differenceHours;
    }

    public BigDecimal getActualAverageYearlyHoursSoFar() {
        return actualAverageYearlyHoursSoFar;
    }

    public void setActualAverageYearlyHoursSoFar(BigDecimal actualAverageYearlyHoursSoFar) {
        this.actualAverageYearlyHoursSoFar = actualAverageYearlyHoursSoFar;
    }

    public BigDecimal getRequiredAverageYearlyHoursRemaining() {
        return requiredAverageYearlyHoursRemaining;
    }

    public void setRequiredAverageYearlyHoursRemaining(BigDecimal requiredAverageYearlyHoursRemaining) {
        this.requiredAverageYearlyHoursRemaining = requiredAverageYearlyHoursRemaining;
    }

    public BigDecimal getTargetYearlyHours() {
        return targetYearlyHours;
    }

    public void setTargetYearlyHours(BigDecimal targetYearlyHours) {
        this.targetYearlyHours = targetYearlyHours;
    }

    public boolean isHoursPlanRealistic() {
        return hoursPlanRealistic;
    }

    public void setHoursPlanRealistic(boolean hoursPlanRealistic) {
        this.hoursPlanRealistic = hoursPlanRealistic;
    }
}
