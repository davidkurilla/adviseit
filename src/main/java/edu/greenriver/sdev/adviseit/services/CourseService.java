package edu.greenriver.sdev.adviseit.services;

import edu.greenriver.sdev.adviseit.domain.CourseDTO;
import edu.greenriver.sdev.adviseit.models.Course;
import edu.greenriver.sdev.adviseit.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void add(CourseDTO course) {
        repository.save(new Course(course.getTitle()));
    }

    public List<CourseDTO> getAll() {
        List<Course> courses = repository.findAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course course : courses) {
            courseDTOS.add(new CourseDTO(course.getId(), course.getTitle()));
        }
        return courseDTOS;
    }

    public CourseDTO getById(int id) {
        Course course = repository.getReferenceById(id);
        return new CourseDTO(course.getId(), course.getTitle());
    }

    public CourseDTO update(int id, Course newCourse) {
        Course course = repository.getReferenceById(id);
        course.setTitle(newCourse.getTitle());
        Course updatedCourse = repository.save(course);
        return new CourseDTO(updatedCourse.getId(), updatedCourse.getTitle());
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
