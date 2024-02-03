package edu.greenriver.sdev.adviseit.apis;

import edu.greenriver.sdev.adviseit.domain.CourseDTO;
import edu.greenriver.sdev.adviseit.models.CourseDAO;
import edu.greenriver.sdev.adviseit.services.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/api/v1/courses")
@Validated
public class CourseAPI {

    private final CourseService service;

    public CourseAPI(CourseService service) {
        this.service = service;
    }

    @PostMapping("/post")
    public ResponseEntity<Object> add(@RequestBody CourseDTO course) {
        service.add(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody CourseDAO newCourse) {
        return new ResponseEntity<>(service.update(id, newCourse), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleBadRequest() {
        return new ResponseEntity<>("400: BAD REQUEST", HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleNotFound() {
        return new ResponseEntity<>("404: NOT FOUND", HttpStatus.NOT_FOUND);
    }

}
