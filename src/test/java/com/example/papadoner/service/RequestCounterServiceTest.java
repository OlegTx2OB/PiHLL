package com.example.papadoner.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestCounterServiceTest {

    @Test
    void testRequestIncrement() {
        RequestCounterService counterService = new RequestCounterService();
        assertEquals(1, counterService.requestIncrement());
        assertEquals(2, counterService.requestIncrement());
        assertEquals(3, counterService.requestIncrement());
    }

    @Test
    void testGetRequestCount() {
        RequestCounterService counterService = new RequestCounterService();
        assertEquals(0, counterService.getRequestCount());
        counterService.requestIncrement();
        assertEquals(1, counterService.getRequestCount());
        counterService.requestIncrement();
        assertEquals(2, counterService.getRequestCount());
    }
}