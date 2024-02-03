package edu.greenriver.sdev.adviseit.database.repositories;

import edu.greenriver.sdev.adviseit.model.entities.StudentPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentPreferencesRepository extends JpaRepository<StudentPreferences, Integer> {
}
