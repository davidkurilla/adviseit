package edu.greenriver.sdev.adviseit.tasks;

import edu.greenriver.sdev.adviseit.model.dto.CourseDTO;
import edu.greenriver.sdev.adviseit.db.objects.CourseDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadCourseStarterDataTask implements CommandLineRunner {

    private final CourseDAO service;

    public LoadCourseStarterDataTask(CourseDAO service) {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception {

        final CourseDTO[] courseDTOS = {
                new CourseDTO("MATH97"),
                new CourseDTO("ENGL101"),
                new CourseDTO("ENGL126"),
                new CourseDTO("MATH141"),
                new CourseDTO("MATH146"),
                new CourseDTO("CMST210"),
                new CourseDTO("Lab Science"),
                new CourseDTO("SDEV101"),
                new CourseDTO("SDEV201"),
                new CourseDTO("SDEV106"),
                new CourseDTO("SDEV117"),
                new CourseDTO("CS108"),
                new CourseDTO("SDEV121"),
                new CourseDTO("SDEV218"),
                new CourseDTO("SDEV219"),
                new CourseDTO("SDEV220"),
                new CourseDTO("SDEV280")
        };

        for (CourseDTO courseDTO : courseDTOS) {
            service.add(courseDTO);
        }
    }
}