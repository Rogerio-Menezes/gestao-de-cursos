package br.com.rogerio.gestaocursos.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository repository;

    @PostMapping("/")
    public ResponseEntity createCourse(@RequestBody CourseDTO course){
        try {
            Course savedCourse = new Course(course);
            this.repository.save(savedCourse);

            return ResponseEntity.ok().body(course);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }

    }

    @GetMapping("/")
    public ResponseEntity getAllCourses(){
        List<Course> courses = this.repository.findAll();
        return ResponseEntity.ok().body(courses);
    }


}
