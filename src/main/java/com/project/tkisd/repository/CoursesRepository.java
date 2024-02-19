package com.project.tkisd.repository;

import com.project.tkisd.domain.Category;
import com.project.tkisd.domain.Courses;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Courses entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoursesRepository extends JpaRepository<Courses, Long> {

    List<Courses> findByCategory(Category category);
}
