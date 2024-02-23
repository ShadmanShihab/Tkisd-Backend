package com.project.tkisd.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InstructorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Instructor getInstructorSample1() {
        return new Instructor()
            .id(1L)
            .instructorName("instructorName1")
            .description("description1")
            .address("address1")
            .phoneNo("phoneNo1")
            .grade(1)
            .user(1L);
    }

    public static Instructor getInstructorSample2() {
        return new Instructor()
            .id(2L)
            .instructorName("instructorName2")
            .description("description2")
            .address("address2")
            .phoneNo("phoneNo2")
            .grade(2)
            .user(2L);
    }

    public static Instructor getInstructorRandomSampleGenerator() {
        return new Instructor()
            .id(longCount.incrementAndGet())
            .instructorName(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .phoneNo(UUID.randomUUID().toString())
            .grade(intCount.incrementAndGet())
            .user(longCount.incrementAndGet());
    }
}
