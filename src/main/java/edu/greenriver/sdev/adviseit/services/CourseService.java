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

    public Course getByName(String title){
        return courseList.get(indexOf(title));
    }

    public Course update(int id, Course newCourse) {
        return courseList.set(id, newCourse);
    }

    public void delete(int id) {
        courseList.remove(id);
    }

    /**
     * gets the index of a course by its title
     * @param title the Course's name
     * @return index of the first Course with given title
     */
    private int indexOf(String title){
        for (int i = 0; i < courseList.size(); i++) {

            if(courseList.get(i).getTitle().equalsIgnoreCase(title)){
                return i;
            }

        }
        return -1;
    }

}
