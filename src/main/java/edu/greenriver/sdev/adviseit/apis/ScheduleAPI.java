package edu.greenriver.sdev.adviseit.apis;

import edu.greenriver.sdev.adviseit.model.entities.Schedule;
import edu.greenriver.sdev.adviseit.services.CourseService;
import edu.greenriver.sdev.adviseit.services.ScheduleService;
import edu.greenriver.sdev.adviseit.services.StudentPreferencesService;
import edu.greenriver.sdev.adviseit.services.factories.ScheduleFactory;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleAPI {

    private final ScheduleFactory scheduleFactory;
    private final ScheduleService scheduleService;
    private final CourseService courseService;
    private final StudentPreferencesService studentPreferencesService;

    public ScheduleAPI(ScheduleFactory scheduleFactory, ScheduleService scheduleService, CourseService courseService, StudentPreferencesService studentPreferencesService) {
        this.scheduleFactory = scheduleFactory;
        this.scheduleService = scheduleService;
        this.courseService = courseService;
        this.studentPreferencesService = studentPreferencesService;
    }

    @PostMapping("/post/{pref}")
    public ResponseEntity<Object> add(@PathVariable int pref){
        Schedule newSchedule = scheduleFactory.build(studentPreferencesService.getById(pref), courseService.getAll());
        scheduleService.add(newSchedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(scheduleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        return new ResponseEntity<>(scheduleService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/put/{id}/{pref}")
    public ResponseEntity<Object> update(@PathVariable int id, @PathVariable int pref) {
        Schedule newSchedule = scheduleFactory.build(studentPreferencesService.getById(pref), courseService.getAll());
        return new ResponseEntity<>(scheduleService.update(id, newSchedule), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        scheduleService.delete(id);
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
