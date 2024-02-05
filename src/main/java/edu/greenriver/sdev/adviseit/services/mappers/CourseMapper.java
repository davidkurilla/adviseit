package edu.greenriver.sdev.adviseit.services.mappers;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import edu.greenriver.sdev.adviseit.model.structures.CourseGraph;
import edu.greenriver.sdev.adviseit.services.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMapper {

    private final List<Course> courseList;
    private final CourseGraph courseGraph;

    public CourseMapper(CourseGraph courseGraph, CourseService courseService) {
        this.courseGraph = courseGraph;
        this.courseList = courseService.getAll();
    }

    public void loadCourses() {
        for (Course course : courseList) {
            courseGraph.addVertex(course);
        }
    }

    public void loadCourseDependencies() {
        for (Course course : courseList) {
            for (Course prerequisite : course.getPrerequisites()) {
                courseGraph.addEdge(prerequisite, course);
            }
        }
    }

    public List<Course> sortAndExportCourses() {
        return courseGraph.sortCourses();
    }
}
