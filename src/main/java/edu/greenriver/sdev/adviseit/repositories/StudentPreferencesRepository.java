package edu.greenriver.sdev.adviseit.repositories;

import edu.greenriver.sdev.adviseit.models.StudentPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPreferencesRepository extends JpaRepository<StudentPreferences, Integer> {
}
