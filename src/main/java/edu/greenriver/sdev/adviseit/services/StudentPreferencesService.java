package edu.greenriver.sdev.adviseit.services;

import edu.greenriver.sdev.adviseit.model.entities.StudentPreferences;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentPreferencesService {

    private final List<StudentPreferences> studentPreferencesList;

    public StudentPreferencesService(){
        studentPreferencesList = new ArrayList<>();
    }

    public void add(StudentPreferences preferences){
        studentPreferencesList.add(preferences);
    }

    public StudentPreferences getById(int id){
        return studentPreferencesList.get(id);
    }

    public List<StudentPreferences> getAll(){
        return studentPreferencesList;
    }

    public StudentPreferences update(int id, StudentPreferences newPreference){
        return studentPreferencesList.set(id, newPreference);
    }

    public void delete(int id){
        studentPreferencesList.remove(id);
    }

}
