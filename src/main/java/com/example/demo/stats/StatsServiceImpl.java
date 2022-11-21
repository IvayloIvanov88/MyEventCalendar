package com.example.demo.stats;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatsServiceImpl implements StatsService {
    private final AtomicInteger requestCount = new AtomicInteger(0);
    private final Instant startedOn = Instant.now();

    @Override
    public Object getRequestCount() {
        return requestCount.get();
    }

    @Override
    public Object getStartedOn() {
        return startedOn;
    }

    @Override
    public void incRequests() {
        requestCount.incrementAndGet();

    }
}
