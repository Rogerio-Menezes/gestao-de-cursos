package br.com.rogerio.gestaocursos.course.service;


import br.com.rogerio.gestaocursos.course.Course;
import br.com.rogerio.gestaocursos.course.CourseDTO;
import br.com.rogerio.gestaocursos.course.CourseRepository;
import br.com.rogerio.gestaocursos.course.DtoCourse;
import br.com.rogerio.gestaocursos.course.exception.CourseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    public ResponseEntity<String> executeCreateCourse(CourseDTO courseDTO) {
        var dto = this.repository.findByName(courseDTO.name()).isPresent();
        if (dto) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This course with that name already exists");
        }
        Course course = new Course(courseDTO);
        this.repository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully");
    }

    public ResponseEntity<List<Course>> executeGetAllCourses() {
        List<Course> courses = this.repository.findAll();
        return ResponseEntity.ok().body(courses);
    }

    public ResponseEntity<Object> updateCourseById(UUID id, DtoCourse courseDTO) {
        var exist = this.repository.findById(id).isPresent();
        if (!exist) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");
        }
        Course course = this.repository.findById(id).get();
        course.setName(courseDTO.name());
        course.setCategory(courseDTO.category());
        course.setShift(courseDTO.shift());
        course.setActive(courseDTO.active());
        course.setUpdated_at(LocalDateTime.now());
        this.repository.save(course);
        return ResponseEntity.ok(course);
    }
    public ResponseEntity<Object> updateCourseActiveStatus(UUID id, boolean active) {
       Course course = repository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found"));

           course.setActive(active);
           this.repository.save(course);
          return ResponseEntity.ok().body(course);


    }
    public ResponseEntity<Course> getCourseById(UUID id) {
        var course = repository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found"));

            return ResponseEntity.ok().body(course);

    }
    public void deleteCourseById(UUID id) {
        var exist = this.repository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course not found"));

        this.repository.deleteById(id);
        ResponseEntity.ok().body("Course deleted successfully");
    }
}
