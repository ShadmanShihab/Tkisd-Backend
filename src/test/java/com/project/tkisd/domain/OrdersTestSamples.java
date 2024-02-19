package com.project.tkisd.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class OrdersTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Orders getOrdersSample1() {
        return new Orders().id(1L).userId(1L);
    }

    public static Orders getOrdersSample2() {
        return new Orders().id(2L).userId(2L);
    }

    public static Orders getOrdersRandomSampleGenerator() {
        return new Orders().id(longCount.incrementAndGet()).userId(longCount.incrementAndGet());
    }
}
