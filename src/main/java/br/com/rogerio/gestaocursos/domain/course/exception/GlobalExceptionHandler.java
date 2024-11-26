package br.com.rogerio.gestaocursos.domain.course.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleCourseNotFoundException(CourseNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (ex.getMessage().contains("Invalid CourseStatus value")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid value for enum 'situation'. Accepted values are: ACTIVE, PENDING, CLOSED, CANCELED.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Malformed request: " + ex.getMessage());
    }

}
