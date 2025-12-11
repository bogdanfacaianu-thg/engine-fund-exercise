package com.example.enginefund.domain;

import com.example.enginefund.api.ProjectionRequest;
import com.example.enginefund.api.ProjectionResponse;

public interface EngineFundService {

    ProjectionResponse project(ProjectionRequest request);
}
