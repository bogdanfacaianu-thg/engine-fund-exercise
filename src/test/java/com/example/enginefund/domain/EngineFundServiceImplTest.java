package com.example.enginefund.domain;

import com.example.enginefund.api.ProjectionRequest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

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
        req.setPlannedAverageYearlyHours(new BigDecimal("200.0"));
        req.setMaxRealisticYearlyHours(new BigDecimal("125.0"));
        req.setEngineFundPerHour(new BigDecimal("30.00"));

        service.project(req);
    }
}
