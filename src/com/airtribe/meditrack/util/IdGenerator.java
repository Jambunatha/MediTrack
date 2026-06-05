package com.airtribe.meditrack.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger COUNTER = new AtomicInteger(1000);

    private IdGenerator() {
        // Private constructor to prevent instantiation
    }

    public static String generate(String prefix) {
        return prefix + COUNTER.incrementAndGet();
    }
}
