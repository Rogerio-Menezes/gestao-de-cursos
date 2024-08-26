package br.com.rogerio.gestaocursos.course.controller;

import br.com.rogerio.gestaocursos.course.Course;
import br.com.rogerio.gestaocursos.course.CourseDTO;
import br.com.rogerio.gestaocursos.course.DtoCourse;
import br.com.rogerio.gestaocursos.course.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createCourse(@Valid @RequestBody CourseDTO course){
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the course");
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> updateCourseActive(@PathVariable UUID id, @RequestParam boolean active){
        try {
            return this.service.updateCourseActiveStatus(id, active);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the course's active status");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID id){
            this.service.deleteCourseById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable UUID id){
        return this.service.getCourseById(id);
    }



}
