package com.example.enginefund.api;

public class ProjectionResponse {

    private HoursSummaryDto hoursSummary;
    private FundProjectionDto fundProjection;

    public HoursSummaryDto getHoursSummary() {
        return hoursSummary;
    }

    public void setHoursSummary(HoursSummaryDto hoursSummary) {
        this.hoursSummary = hoursSummary;
    }

    public FundProjectionDto getFundProjection() {
        return fundProjection;
    }

    public void setFundProjection(FundProjectionDto fundProjection) {
        this.fundProjection = fundProjection;
    }
}
