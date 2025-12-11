package com.example.enginefund.domain;

import com.example.enginefund.api.ProjectionRequest;
import com.example.enginefund.api.ProjectionResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class EngineFundServiceImplTest {

    private final EngineFundServiceImpl service = new EngineFundServiceImpl();

    @Test
    void exampleScenarioProducesExpectedFinalBalanceAndSuggestions() {
        ProjectionRequest req = new ProjectionRequest();
        req.setCurrentFundBalance(new BigDecimal("10000.00"));
        req.setTargetFund(new BigDecimal("45000.00"));
        req.setTotalPeriodYears(new BigDecimal("10.0"));
        req.setYearsElapsed(new BigDecimal("5.0"));
        req.setHoursFlownSoFar(new BigDecimal("700.0"));
        req.setTargetYearlyHours(new BigDecimal("125.0"));
        req.setEngineFundPerHour(new BigDecimal("30.00"));

        ProjectionResponse response = service.project(req);

        assertThat(response.getHoursSummary().getExpectedHoursSoFar())
            .isEqualByComparingTo(new BigDecimal("625.0"));

        assertThat(response.getHoursSummary().getDifferenceHours())
            .isEqualByComparingTo(new BigDecimal("75.0"));

        assertThat(response.getHoursSummary().getActualAverageYearlyHoursSoFar())
            .isEqualByComparingTo(new BigDecimal("140.0"));

        assertThat(response.getHoursSummary().getRequiredAverageYearlyHoursRemaining())
            .isEqualByComparingTo(new BigDecimal("110.0"));

        assertThat(response.getHoursSummary().isHoursPlanRealistic())
            .isTrue();

        assertThat(response.getFundProjection().getFinalProjectedBalance())
            .isEqualByComparingTo(new BigDecimal("28750.00"));

        assertThat(response.getFundProjection().getDifferenceAtEnd())
            .isEqualByComparingTo(new BigDecimal("-16250.00"));

        assertThat(response.getFundProjection().getSuggestedCashCall())
            .isEqualByComparingTo(new BigDecimal("16250.00"));

        assertThat(response.getFundProjection().getSuggestedYearlyCashCall())
            .isEqualByComparingTo(new BigDecimal("3250.00"));

        assertThat(response.getFundProjection().getSuggestedEngineFundPerHour())
            .isEqualByComparingTo(new BigDecimal("56.00"));
    }
}
