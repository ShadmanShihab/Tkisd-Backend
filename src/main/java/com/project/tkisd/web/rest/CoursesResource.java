package com.project.tkisd.web.rest;

import com.project.tkisd.domain.Category;
import com.project.tkisd.domain.Courses;
import com.project.tkisd.repository.CategoryRepository;
import com.project.tkisd.repository.CoursesRepository;
import com.project.tkisd.service.CategoryService;
import com.project.tkisd.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.project.tkisd.domain.Courses}.
 */
@RestController
@RequestMapping("/api/courses")
@Transactional
public class CoursesResource {

    private final Logger log = LoggerFactory.getLogger(CoursesResource.class);

    private static final String ENTITY_NAME = "courses";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CoursesRepository coursesRepository;

    private final CategoryService categoryService;

    public CoursesResource(CoursesRepository coursesRepository, CategoryService categoryService) {
        this.coursesRepository = coursesRepository;
        this.categoryService = categoryService;
    }

    /**
     * {@code POST  /courses} : Create a new courses.
     *
     * @param courses the courses to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new courses, or with status {@code 400 (Bad Request)} if the courses has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Courses> createCourses(@Valid @RequestBody Courses courses) throws URISyntaxException {
        log.debug("REST request to save Courses : {}", courses);
        if (courses.getId() != null) {
            throw new BadRequestAlertException("A new courses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Courses result = coursesRepository.save(courses);
        return ResponseEntity
            .created(new URI("/api/courses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /courses/:id} : Updates an existing courses.
     *
     * @param id the id of the courses to save.
     * @param courses the courses to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courses,
     * or with status {@code 400 (Bad Request)} if the courses is not valid,
     * or with status {@code 500 (Internal Server Error)} if the courses couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Courses> updateCourses(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Courses courses
    ) throws URISyntaxException {
        log.debug("REST request to update Courses : {}, {}", id, courses);
        if (courses.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, courses.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coursesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Courses result = coursesRepository.save(courses);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, courses.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /courses/:id} : Partial updates given fields of an existing courses, field will ignore if it is null
     *
     * @param id the id of the courses to save.
     * @param courses the courses to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated courses,
     * or with status {@code 400 (Bad Request)} if the courses is not valid,
     * or with status {@code 404 (Not Found)} if the courses is not found,
     * or with status {@code 500 (Internal Server Error)} if the courses couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Courses> partialUpdateCourses(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Courses courses
    ) throws URISyntaxException {
        log.debug("REST request to partial update Courses partially : {}, {}", id, courses);
        if (courses.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, courses.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!coursesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Courses> result = coursesRepository
            .findById(courses.getId())
            .map(existingCourses -> {
                if (courses.getCourseName() != null) {
                    existingCourses.setCourseName(courses.getCourseName());
                }
                if (courses.getDescription() != null) {
                    existingCourses.setDescription(courses.getDescription());
                }
                if (courses.getPrice() != null) {
                    existingCourses.setPrice(courses.getPrice());
                }
                if (courses.getNumberOfClasses() != null) {
                    existingCourses.setNumberOfClasses(courses.getNumberOfClasses());
                }
                if (courses.getTotalDuration() != null) {
                    existingCourses.setTotalDuration(courses.getTotalDuration());
                }

                return existingCourses;
            })
            .map(coursesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, courses.getId().toString())
        );
    }

    /**
     * {@code GET  /courses} : get all the courses.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of courses in body.
     */
    @GetMapping("")
    public List<Courses> getAllCourses(@RequestParam(name = "filter", required = false) String filter) {
        if ("orders-is-null".equals(filter)) {
            log.debug("REST request to get all Coursess where orders is null");
            return StreamSupport
                .stream(coursesRepository.findAll().spliterator(), false)
//                .filter(courses -> courses.getOrders() == null)
                .toList();
        }
        log.debug("REST request to get all Courses");
        return coursesRepository.findAll();
    }

    @GetMapping("/category/{categoryId}")
    public List<Courses> getAllCoursesByCategoryid(@PathVariable("categoryId") Long categoryId) {
        log.debug("REST request to get Courses by category id : {}", categoryId);
        Category category = categoryService.findById(categoryId);
        List<Courses> courses = coursesRepository.findByCategory(category);
        return courses;
    }

    /**
     * {@code GET  /courses/:id} : get the "id" courses.
     *
     * @param id the id of the courses to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the courses, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Courses> getCourses(@PathVariable("id") Long id) {
        log.debug("REST request to get Courses : {}", id);
        Optional<Courses> courses = coursesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(courses);
    }

    /**
     * {@code DELETE  /courses/:id} : delete the "id" courses.
     *
     * @param id the id of the courses to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourses(@PathVariable("id") Long id) {
        log.debug("REST request to delete Courses : {}", id);
        coursesRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
