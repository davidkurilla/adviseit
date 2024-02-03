package edu.greenriver.sdev.adviseit.services;

import edu.greenriver.sdev.adviseit.domain.CourseDTO;
import edu.greenriver.sdev.adviseit.domain.StudentPreferencesDTO;
import edu.greenriver.sdev.adviseit.models.StudentPreferencesDAO;
import edu.greenriver.sdev.adviseit.repositories.StudentPreferencesRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentPreferencesService {

    private StudentPreferencesRepository repository;

    public StudentPreferencesService(StudentPreferencesRepository repository){
        this.repository = repository;
    }


    public void add(StudentPreferencesDTO preferences){
        repository.save(new StudentPreferencesDAO(preferences.getCoursesPerQuarter(), preferences.getSummerQuarter()));
    }

    public StudentPreferencesDTO getById(int id){
        StudentPreferencesDAO preference = repository.getReferenceById(id);
        return new StudentPreferencesDTO(preference.getId(), preference.getCoursesPerQuarter(), preference.getSummerQuarter());
    }

    public List<StudentPreferencesDTO> getAll(){
        List<StudentPreferencesDAO> preferences = repository.findAll();
        List<StudentPreferencesDTO> preferenceDTOS = new ArrayList<>();
        for(StudentPreferencesDAO preference : preferences){
            preferenceDTOS.add(new StudentPreferencesDTO(preference.getId(), preference.getCoursesPerQuarter(), preference.getSummerQuarter()));
        }
        return preferenceDTOS;
    }

    public StudentPreferencesDTO update(int id, StudentPreferencesDAO newPreference){
        StudentPreferencesDAO preference = repository.getReferenceById(id);
        preference.setCoursesPerQuarter(newPreference.getCoursesPerQuarter());
        preference.setSummerQuarter(newPreference.getSummerQuarter());
        StudentPreferencesDAO updatedPreference = repository.save(preference);
        return new StudentPreferencesDTO(updatedPreference.getId(), updatedPreference.getCoursesPerQuarter(), updatedPreference.getSummerQuarter());
    }

    public void delete(int id){
        repository.deleteById(id);
    }

}
