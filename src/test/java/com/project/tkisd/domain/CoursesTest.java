package com.project.tkisd.domain;

import static com.project.tkisd.domain.CategoryTestSamples.*;
import static com.project.tkisd.domain.CoursesTestSamples.*;
import static com.project.tkisd.domain.OrdersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.tkisd.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CoursesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Courses.class);
        Courses courses1 = getCoursesSample1();
        Courses courses2 = new Courses();
        assertThat(courses1).isNotEqualTo(courses2);

        courses2.setId(courses1.getId());
        assertThat(courses1).isEqualTo(courses2);

        courses2 = getCoursesSample2();
        assertThat(courses1).isNotEqualTo(courses2);
    }

    @Test
    void categoryIdTest() throws Exception {
        Courses courses = getCoursesRandomSampleGenerator();
        Category categoryBack = getCategoryRandomSampleGenerator();

        courses.setCategoryId(categoryBack);
        assertThat(courses.getCategory()).isEqualTo(categoryBack);

        courses.categoryId(null);
        assertThat(courses.getCategory()).isNull();
    }

    @Test
    void ordersTest() throws Exception {
        Courses courses = getCoursesRandomSampleGenerator();
        Orders ordersBack = getOrdersRandomSampleGenerator();

        courses.setOrders(ordersBack);
        assertThat(courses.getOrders()).isEqualTo(ordersBack);
        assertThat(ordersBack.getCourseId()).isEqualTo(courses);

        courses.orders(null);
        assertThat(courses.getOrders()).isNull();
        assertThat(ordersBack.getCourseId()).isNull();
    }
}
