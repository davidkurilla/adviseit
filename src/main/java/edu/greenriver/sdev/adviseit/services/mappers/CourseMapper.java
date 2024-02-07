package edu.greenriver.sdev.adviseit.services.mappers;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import edu.greenriver.sdev.adviseit.model.structures.CourseGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseMapper {

    private final CourseGraph courseGraph;

    public CourseMapper(CourseGraph courseGraph) {
        this.courseGraph = courseGraph;
    }

    public List<Course> build(List<Course> courseList) {
        for (Course course : courseList) {
            courseGraph.addVertex(course);
            for (Course prerequisite : course.getPrerequisites()) {
                courseGraph.addEdge(prerequisite, course);
            }
        }
        return courseGraph.sortCourses();
    }
}
