package com.project.tkisd.domain;

import static com.project.tkisd.domain.CoursesTestSamples.*;
import static com.project.tkisd.domain.OrdersTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.project.tkisd.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrdersTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Orders.class);
        Orders orders1 = getOrdersSample1();
        Orders orders2 = new Orders();
        assertThat(orders1).isNotEqualTo(orders2);

        orders2.setId(orders1.getId());
        assertThat(orders1).isEqualTo(orders2);

        orders2 = getOrdersSample2();
        assertThat(orders1).isNotEqualTo(orders2);
    }

    @Test
    void courseIdTest() throws Exception {
        Orders orders = getOrdersRandomSampleGenerator();
        Courses coursesBack = getCoursesRandomSampleGenerator();

        orders.setCourseId(coursesBack);
        assertThat(orders.getCourseId()).isEqualTo(coursesBack);

        orders.courseId(null);
        assertThat(orders.getCourseId()).isNull();
    }
}
