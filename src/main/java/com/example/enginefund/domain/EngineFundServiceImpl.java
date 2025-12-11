package com.example.enginefund.domain;

import com.example.enginefund.api.FundProjectionDto;
import com.example.enginefund.api.HoursSummaryDto;
import com.example.enginefund.api.ProjectionRequest;
import com.example.enginefund.api.ProjectionResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EngineFundServiceImpl implements EngineFundService {

    @Override
    public ProjectionResponse project(ProjectionRequest request) {
        ProjectionResponse response = new ProjectionResponse();
        HoursSummaryDto hs = new HoursSummaryDto();
        FundProjectionDto fp = new FundProjectionDto();

        BigDecimal totalYears = request.getTotalPeriodYears();
        BigDecimal elapsed = request.getYearsElapsed();
        if (totalYears == null || elapsed == null) {
            throw new IllegalArgumentException("Years must not be null");
        }
        BigDecimal zero = new BigDecimal(0);
        BigDecimal oneHundred = new BigDecimal("100.00");

        BigDecimal yearsRemaining = totalYears.subtract(elapsed);

        BigDecimal realisticHoursPerYear = request.getTargetYearlyHours();
        BigDecimal hoursSoFar = request.getHoursFlownSoFar();

        if (realisticHoursPerYear == null) {
            realisticHoursPerYear = zero;
        }
        if (hoursSoFar == null) {
            hoursSoFar = zero;
        }

        BigDecimal expectedHoursSoFar = realisticHoursPerYear.multiply(elapsed);
        BigDecimal differenceHours = hoursSoFar.subtract(expectedHoursSoFar);

        BigDecimal actualAvgSoFar;
        if (elapsed.compareTo(zero) > 0) {
            actualAvgSoFar = hoursSoFar.divide(elapsed, 4, RoundingMode.HALF_UP);
        } else {
            actualAvgSoFar = zero;
        }

        BigDecimal totalPlannedHours = totalYears.multiply(realisticHoursPerYear);
        BigDecimal remainingHoursNeeded = totalPlannedHours.subtract(hoursSoFar);

        BigDecimal requiredAvgRemaining;
        if (yearsRemaining.compareTo(zero) > 0) {
            requiredAvgRemaining = remainingHoursNeeded.divide(yearsRemaining, 4, RoundingMode.HALF_UP);
        } else {
            requiredAvgRemaining = zero;
        }

        boolean realistic = false;
        if (requiredAvgRemaining.compareTo(realisticHoursPerYear) >= 0) {
            realistic = true;
        }

        hs.setTotalPeriodYears(totalYears);
        hs.setYearsElapsed(elapsed);
        hs.setYearsRemaining(yearsRemaining);
        hs.setHoursFlownSoFar(hoursSoFar);
        hs.setExpectedHoursSoFar(expectedHoursSoFar);
        hs.setDifferenceHours(differenceHours);
        hs.setActualAverageYearlyHoursSoFar(actualAvgSoFar);
        hs.setRequiredAverageYearlyHoursRemaining(requiredAvgRemaining);
        hs.setTargetYearlyHours(realisticHoursPerYear);
        hs.setHoursPlanRealistic(realistic);

        BigDecimal currentFund = request.getCurrentFundBalance();
        BigDecimal targetFund = request.getTargetFund();
        if (currentFund == null) currentFund = zero;
        if (targetFund == null) targetFund = zero;

        BigDecimal efh = request.getEngineFundPerHour();
        if (efh == null) {
            efh = zero;
        }

        BigDecimal remainingHours = yearsRemaining.multiply(realisticHoursPerYear);
        BigDecimal finalProjectedBalance = currentFund.add(remainingHours.multiply(efh));

        BigDecimal differenceAtEnd = finalProjectedBalance.subtract(targetFund);
        BigDecimal suggestedCashCall = zero;
        if (differenceAtEnd.compareTo(zero) < 0) {
            suggestedCashCall = differenceAtEnd;
        }

        // TODO: Implement suggested yearly cash call calculation
        BigDecimal suggestedYearlyCashCall = zero;

        // TODO: Implement suggested engine fund per hour calculation
        BigDecimal suggestedEfh = null;

        fp.setCurrentFundBalance(scaleMoney(currentFund));
        fp.setTargetFund(scaleMoney(targetFund));
        fp.setYearsRemaining(yearsRemaining);
        fp.setRemainingHours(remainingHours);
        fp.setEngineFundPerHourCurrent(scaleMoney(efh));
        fp.setFinalProjectedBalance(finalProjectedBalance);
        fp.setDifferenceAtEnd(scaleMoney(differenceAtEnd));
        fp.setSuggestedCashCall(scaleMoney(suggestedCashCall));
        fp.setSuggestedEngineFundPerHour(scaleMoney(suggestedEfh));

        response.setHoursSummary(hs);
        response.setFundProjection(fp);
        return response;
    }

    private BigDecimal scaleMoney(BigDecimal value) {
        if (value == null) {
            return null;
        }
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}
