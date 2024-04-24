package com.example.papadoner.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestCounterServiceTest {

    @Test
    public void testRequestIncrement() {
        RequestCounterService counterService = new RequestCounterService();
        assertEquals(1, counterService.requestIncrement());
        assertEquals(2, counterService.requestIncrement());
        assertEquals(3, counterService.requestIncrement());
    }

    @Test
    public void testGetRequestCount() {
        RequestCounterService counterService = new RequestCounterService();
        assertEquals(0, counterService.getRequestCount());
        counterService.requestIncrement();
        assertEquals(1, counterService.getRequestCount());
        counterService.requestIncrement();
        assertEquals(2, counterService.getRequestCount());
    }
}