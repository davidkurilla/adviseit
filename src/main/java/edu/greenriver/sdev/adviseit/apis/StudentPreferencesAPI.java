package edu.greenriver.sdev.adviseit.apis;

import edu.greenriver.sdev.adviseit.model.dto.StudentPreferencesDTO;
import edu.greenriver.sdev.adviseit.model.entities.StudentPreferences;
import edu.greenriver.sdev.adviseit.db.objects.StudentPreferencesDAO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("api/v1/preferences")
@Validated
public class StudentPreferencesAPI {

    private final StudentPreferencesDAO service;

    public StudentPreferencesAPI(StudentPreferencesDAO service){
        this.service = service;
    }

    @PostMapping("/post")
    public ResponseEntity<Object> add(@RequestBody StudentPreferencesDTO preference){
        service.add(preference);
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
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody StudentPreferences newPreference) {
        return new ResponseEntity<>(service.update(id, newPreference), HttpStatus.OK);
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
