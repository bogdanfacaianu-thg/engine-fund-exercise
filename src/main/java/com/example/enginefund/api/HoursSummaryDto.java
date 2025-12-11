package com.example.enginefund.api;

import java.math.BigDecimal;

public class HoursSummaryDto {

    private BigDecimal totalPeriodYears;
    private BigDecimal yearsElapsed;
    private BigDecimal yearsRemaining;
    private BigDecimal hoursFlownSoFar;
    private BigDecimal plannedAverageYearlyHours;
    private BigDecimal expectedHoursSoFar;
    private BigDecimal differenceHours;
    private BigDecimal actualAverageYearlyHoursSoFar;
    private BigDecimal requiredAverageYearlyHoursRemaining;
    private BigDecimal maxRealisticYearlyHours;
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

    public BigDecimal getPlannedAverageYearlyHours() {
        return plannedAverageYearlyHours;
    }

    public void setPlannedAverageYearlyHours(BigDecimal plannedAverageYearlyHours) {
        this.plannedAverageYearlyHours = plannedAverageYearlyHours;
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

    public BigDecimal getMaxRealisticYearlyHours() {
        return maxRealisticYearlyHours;
    }

    public void setMaxRealisticYearlyHours(BigDecimal maxRealisticYearlyHours) {
        this.maxRealisticYearlyHours = maxRealisticYearlyHours;
    }

    public boolean isHoursPlanRealistic() {
        return hoursPlanRealistic;
    }

    public void setHoursPlanRealistic(boolean hoursPlanRealistic) {
        this.hoursPlanRealistic = hoursPlanRealistic;
    }
}
