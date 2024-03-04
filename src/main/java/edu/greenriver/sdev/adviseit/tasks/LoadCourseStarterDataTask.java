package edu.greenriver.sdev.adviseit.tasks;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import edu.greenriver.sdev.adviseit.services.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * loads a list of classes and their prerequisites
 * @version 1.0
 */
@Component
@Order(1)
public class LoadCourseStarterDataTask implements CommandLineRunner {

    private final CourseService service;

    public LoadCourseStarterDataTask(CourseService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

        final Course[] courseDTOS = {
                new Course("MATH97", new ArrayList<>()),
                new Course("ENGL101", new ArrayList<>()),
                new Course("ENGL126", new ArrayList<>()),
                new Course("MATH141", new ArrayList<>()),
                new Course("MATH146", new ArrayList<>()),
                new Course("CMST210", new ArrayList<>()),
                new Course("Lab Science", new ArrayList<>()),
                new Course("SDEV101", new ArrayList<>()),
                new Course("SDEV201", new ArrayList<>()),
                new Course("SDEV106", new ArrayList<>()),
                new Course("SDEV117", new ArrayList<>()),
                new Course("CS108", new ArrayList<>()),
                new Course("SDEV121", new ArrayList<>()),
                new Course("SDEV218", new ArrayList<>()),
                new Course("SDEV219", new ArrayList<>()),
                new Course("SDEV220", new ArrayList<>()),
                new Course("SDEV280", new ArrayList<>())
        };

        for (Course courseDTO : courseDTOS) {
            service.add(courseDTO);
        }

        service.update(10, new Course("SDEV117", new ArrayList<>(List.of(service.getById(9)))));
        service.update(11, new Course("CS108", new ArrayList<>(List.of(service.getById(0)))));
        service.update(12, new Course("SDEV121", new ArrayList<>(List.of(service.getById(11)))));
        service.update(13, new Course("SDEV218", new ArrayList<>(List.of(service.getById(0)))));
        service.update(14, new Course("SDEV219", new ArrayList<>(List.of(service.getById(13)))));
        service.update(15, new Course("SDEV220", new ArrayList<>(List.of(service.getById(14)))));
    }
}