package com.project.tkisd.domain;

import static com.project.tkisd.domain.CategoryTestSamples.*;
import static com.project.tkisd.domain.CoursesTestSamples.*;
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

        courses.setCategory(categoryBack);
        assertThat(courses.getCategory()).isEqualTo(categoryBack);

        courses.category(null);
        assertThat(courses.getCategory()).isNull();
    }
}
