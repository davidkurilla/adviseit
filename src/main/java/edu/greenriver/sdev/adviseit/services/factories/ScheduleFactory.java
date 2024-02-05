package edu.greenriver.sdev.adviseit.services.factories;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import edu.greenriver.sdev.adviseit.services.mappers.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleFactory {

    private final CourseMapper courseMapper;

    public ScheduleFactory(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public List<Course> buildCourseMap() {
        courseMapper.loadCourses();
        courseMapper.loadCourseDependencies();
        return courseMapper.sortAndExportCourses();
    }
}
