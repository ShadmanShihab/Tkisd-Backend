package com.project.tkisd.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CoursesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Courses getCoursesSample1() {
        return new Courses().id(1L).courseName("courseName1").description("description1").numberOfClasses(1L).totalDuration(1L);
    }

    public static Courses getCoursesSample2() {
        return new Courses().id(2L).courseName("courseName2").description("description2").numberOfClasses(2L).totalDuration(2L);
    }

    public static Courses getCoursesRandomSampleGenerator() {
        return new Courses()
            .id(longCount.incrementAndGet())
            .courseName(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .numberOfClasses(longCount.incrementAndGet())
            .totalDuration(longCount.incrementAndGet());
    }
}
