package com.example.demo.stats;

public interface StatsService {

    Object getRequestCount();

    Object getStartedOn();

    void incRequests();
}
