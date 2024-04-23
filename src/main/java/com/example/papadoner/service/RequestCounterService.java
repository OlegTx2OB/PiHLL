package com.example.papadoner.service;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Service
public class RequestCounterService {
    private final AtomicInteger requestCount = new AtomicInteger(0);

    public int requestIncrement() {
        return requestCount.incrementAndGet();
    }

    public int getRequestCount() {
        return requestCount.get();
    }
}