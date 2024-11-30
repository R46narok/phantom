package com.phantom.controller;

import com.phantom.proxy.ProxyOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/proxy")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProxyController {

    private final ProxyOrchestrator orchestrator;

    @GetMapping
    public List<Long> getAllRequests() {
        return orchestrator.getProviders();
    }
}
