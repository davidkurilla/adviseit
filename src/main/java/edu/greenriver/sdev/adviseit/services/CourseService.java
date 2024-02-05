package edu.greenriver.sdev.adviseit.services;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final List<Course> courseList;

    public CourseService() {
        courseList = new ArrayList<>();
    }

    public void add(Course course) {
        courseList.add(course);
    }

    public List<Course> getAll() {
        return courseList;
    }

    public Course getById(int id) {
        return courseList.get(id);
    }

    public Course update(int id, Course newCourse) {
        return courseList.set(id, newCourse);
    }

    public void delete(int id) {
        courseList.remove(id);
    }
}
