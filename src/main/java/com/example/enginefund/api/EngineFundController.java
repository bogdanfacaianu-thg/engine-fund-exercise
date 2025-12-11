package com.example.enginefund.api;

import com.example.enginefund.domain.EngineFundService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/engine-fund")
public class EngineFundController {

    private final EngineFundService service;

    public EngineFundController(EngineFundService service) {
        this.service = service;
    }

    @PostMapping("/projection")
    public ProjectionResponse project(@RequestBody ProjectionRequest request) {
        return service.project(request);
    }
}
