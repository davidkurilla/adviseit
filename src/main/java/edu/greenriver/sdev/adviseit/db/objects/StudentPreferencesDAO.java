package edu.greenriver.sdev.adviseit.db.objects;

import edu.greenriver.sdev.adviseit.model.dto.StudentPreferencesDTO;
import edu.greenriver.sdev.adviseit.model.entities.StudentPreferences;
import edu.greenriver.sdev.adviseit.db.repositories.StudentPreferencesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class StudentPreferencesDAO {

    private StudentPreferencesRepository repository;

    public StudentPreferencesDAO(StudentPreferencesRepository repository){
        this.repository = repository;
    }


    public void add(StudentPreferencesDTO preferences){
        repository.save(new StudentPreferences(preferences.getCoursesPerQuarter(), preferences.getSummerQuarter()));
    }

    public StudentPreferencesDTO getById(int id){
        StudentPreferences preference = repository.getReferenceById(id);
        return new StudentPreferencesDTO(preference.getId(), preference.getCoursesPerQuarter(), preference.getSummerQuarter());
    }

    public List<StudentPreferencesDTO> getAll(){
        List<StudentPreferences> preferences = repository.findAll();
        List<StudentPreferencesDTO> preferenceDTOS = new ArrayList<>();
        for(StudentPreferences preference : preferences){
            preferenceDTOS.add(new StudentPreferencesDTO(preference.getId(), preference.getCoursesPerQuarter(), preference.getSummerQuarter()));
        }
        return preferenceDTOS;
    }

    public StudentPreferencesDTO update(int id, StudentPreferences newPreference){
        StudentPreferences preference = repository.getReferenceById(id);
        preference.setCoursesPerQuarter(newPreference.getCoursesPerQuarter());
        preference.setSummerQuarter(newPreference.getSummerQuarter());
        StudentPreferences updatedPreference = repository.save(preference);
        return new StudentPreferencesDTO(updatedPreference.getId(), updatedPreference.getCoursesPerQuarter(), updatedPreference.getSummerQuarter());
    }

    public void delete(int id){
        repository.deleteById(id);
    }

}
