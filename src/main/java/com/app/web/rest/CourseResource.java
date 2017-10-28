package com.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.app.domain.Course;
import com.app.domain.Student;
import com.app.repository.CourseRepository;
import com.app.repository.StudentRepository;
import com.app.web.rest.util.HeaderUtil;
import com.app.web.rest.util.PaginationUtil;
import com.app.service.dto.CourseDTO;
import com.app.service.dto.StudentDTO;
import com.app.service.mapper.CourseMapper;
import com.app.service.mapper.StudentMapper;

import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class CourseResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    private static final String ENTITY_NAME = "course";

    private final CourseRepository courseRepository;
    
    private final StudentRepository studentRepository;

    private final CourseMapper courseMapper;
    
    private final StudentMapper studentMapper;

    public CourseResource(CourseRepository courseRepository, CourseMapper courseMapper, StudentRepository studentRepository, StudentMapper studentMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.studentRepository= studentRepository;
        this.studentMapper = studentMapper;
    }

    /**
     * POST  /courses : Create a new course.
     *
     * @param courseDTO the courseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseDTO, or with status 400 (Bad Request) if the course has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/courses")
    @Timed
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {
        log.debug("REST request to save Course : {}", courseDTO);
        if (courseDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new course cannot already have an ID")).body(null);
        }
        Course course = courseMapper.toEntity(courseDTO);
        course = courseRepository.save(course);
        CourseDTO result = courseMapper.toDto(course);
        return ResponseEntity.created(new URI("/api/courses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /courses : Updates an existing course.
     *
     * @param courseDTO the courseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseDTO,
     * or with status 400 (Bad Request) if the courseDTO is not valid,
     * or with status 500 (Internal Server Error) if the courseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/courses")
    @Timed
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {
        log.debug("REST request to update Course : {}", courseDTO);
        if (courseDTO.getId() == null) {
            return createCourse(courseDTO);
        }
        Course course = courseMapper.toEntity(courseDTO);
        course = courseRepository.save(course);
        CourseDTO result = courseMapper.toDto(course);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /courses : get all the courses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of courses in body
     */
    @GetMapping("/courses")
    @Timed
    public ResponseEntity<List<CourseDTO>> getAllCourses(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Courses");
        Page<Course> page = courseRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/courses");
        return new ResponseEntity<>(courseMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /courses/:id : get the "id" course.
     *
     * @param id the id of the courseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/courses/{id}")
    @Timed
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        Course course = courseRepository.findOneWithEagerRelationships(id);
        log.debug("++++++++++++++++++ CURSO:"+course.getName()+" estudiantes "+course.getStudents());
        CourseDTO courseDTO = courseMapper.toDto(course);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(course));
    }

    /**
     * DELETE  /courses/:id : delete the "id" course.
     *
     * @param id the id of the courseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/courses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
    
    @PostMapping("/courses/student/{id}")
    @Timed
    public ResponseEntity<CourseDTO> addStudentToCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
    	log.debug("REST request to add Student : {}", id," to course ", courseDTO.getId());
    	Course course = courseMapper.toEntity(courseDTO);
    	Student student = studentRepository.findOne(id);
    	StudentDTO studentDto = studentMapper.toDto(student);
    	course = courseRepository.save(course);
        CourseDTO result = courseMapper.toDto(course);
        Set<StudentDTO> students = new HashSet<StudentDTO>();
        students = result.getStudents();
        students.add(studentDto);
        result.setStudents(students);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
}
