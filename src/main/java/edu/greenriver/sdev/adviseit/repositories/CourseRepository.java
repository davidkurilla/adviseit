package edu.greenriver.sdev.adviseit.repositories;

import edu.greenriver.sdev.adviseit.models.CourseDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseDAO, Integer> {
}
