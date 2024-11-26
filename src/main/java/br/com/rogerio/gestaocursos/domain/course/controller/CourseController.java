package br.com.rogerio.gestaocursos.domain.course.controller;

import br.com.rogerio.gestaocursos.domain.course.Course;
import br.com.rogerio.gestaocursos.domain.course.dto.CourseDTO;
import br.com.rogerio.gestaocursos.domain.course.dto.DtoCourse;
import br.com.rogerio.gestaocursos.domain.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/course")
@Validated
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createCourse(@Valid @RequestBody CourseDTO course){
        try {
           return this.service.executeCreateCourse(course);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the course");
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses(){
        return this.service.executeGetAllCourses();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCourse(@PathVariable UUID id, @RequestBody DtoCourse courseDTO){
        try {
            return this.service.updateCourseById(id, courseDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred while updating the course");
        }
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> updateCourseActive(@PathVariable UUID id, @RequestParam String situation){
        try {
            return this.service.updateCourseActiveStatus(id, situation);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the course's active status");
        }
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID id){
            this.service.deleteCourseById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id){
        return this.service.getCourseById(id);
    }



}
