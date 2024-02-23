package com.project.tkisd.web.rest;

import com.project.tkisd.domain.Instructor;
import com.project.tkisd.repository.InstructorRepository;
import com.project.tkisd.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.project.tkisd.domain.Instructor}.
 */
@RestController
@RequestMapping("/api/instructors")
@Transactional
public class InstructorResource {

    private final Logger log = LoggerFactory.getLogger(InstructorResource.class);

    private static final String ENTITY_NAME = "instructor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstructorRepository instructorRepository;

    public InstructorResource(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    /**
     * {@code POST  /instructors} : Create a new instructor.
     *
     * @param instructor the instructor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instructor, or with status {@code 400 (Bad Request)} if the instructor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor) throws URISyntaxException {
        log.debug("REST request to save Instructor : {}", instructor);
        if (instructor.getId() != null) {
            throw new BadRequestAlertException("A new instructor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Instructor result = instructorRepository.save(instructor);
        return ResponseEntity
            .created(new URI("/api/instructors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /instructors/:id} : Updates an existing instructor.
     *
     * @param id the id of the instructor to save.
     * @param instructor the instructor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instructor,
     * or with status {@code 400 (Bad Request)} if the instructor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instructor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Instructor instructor
    ) throws URISyntaxException {
        log.debug("REST request to update Instructor : {}, {}", id, instructor);
        if (instructor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, instructor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!instructorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Instructor result = instructorRepository.save(instructor);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, instructor.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /instructors/:id} : Partial updates given fields of an existing instructor, field will ignore if it is null
     *
     * @param id the id of the instructor to save.
     * @param instructor the instructor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instructor,
     * or with status {@code 400 (Bad Request)} if the instructor is not valid,
     * or with status {@code 404 (Not Found)} if the instructor is not found,
     * or with status {@code 500 (Internal Server Error)} if the instructor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Instructor> partialUpdateInstructor(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Instructor instructor
    ) throws URISyntaxException {
        log.debug("REST request to partial update Instructor partially : {}, {}", id, instructor);
        if (instructor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, instructor.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!instructorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Instructor> result = instructorRepository
            .findById(instructor.getId())
            .map(existingInstructor -> {
                if (instructor.getInstructorName() != null) {
                    existingInstructor.setInstructorName(instructor.getInstructorName());
                }
                if (instructor.getDescription() != null) {
                    existingInstructor.setDescription(instructor.getDescription());
                }
                if (instructor.getAddress() != null) {
                    existingInstructor.setAddress(instructor.getAddress());
                }
                if (instructor.getPhoneNo() != null) {
                    existingInstructor.setPhoneNo(instructor.getPhoneNo());
                }
                if (instructor.getGrade() != null) {
                    existingInstructor.setGrade(instructor.getGrade());
                }
                if (instructor.getUser() != null) {
                    existingInstructor.setUser(instructor.getUser());
                }

                return existingInstructor;
            })
            .map(instructorRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, instructor.getId().toString())
        );
    }

    /**
     * {@code GET  /instructors} : get all the instructors.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instructors in body.
     */
    @GetMapping("")
    public List<Instructor> getAllInstructors() {
        log.debug("REST request to get all Instructors");
        return instructorRepository.findAll();
    }

    /**
     * {@code GET  /instructors/:id} : get the "id" instructor.
     *
     * @param id the id of the instructor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instructor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructor(@PathVariable("id") Long id) {
        log.debug("REST request to get Instructor : {}", id);
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(instructor);
    }

    /**
     * {@code DELETE  /instructors/:id} : delete the "id" instructor.
     *
     * @param id the id of the instructor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable("id") Long id) {
        log.debug("REST request to delete Instructor : {}", id);
        instructorRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
