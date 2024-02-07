package edu.greenriver.sdev.adviseit.services;

import edu.greenriver.sdev.adviseit.model.entities.Schedule;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private List<Schedule> scheduleList;

    public ScheduleService() {
        scheduleList = new ArrayList<>();
    }

    public void add(Schedule schedule){
        scheduleList.add(schedule);
    }

    public Schedule getById(int id){
        return scheduleList.get(id);
    }

    public List<Schedule> getAll(){
        return scheduleList;
    }

    public Schedule update(int id, Schedule newSchedule){
        return scheduleList.set(id, newSchedule);
    }

    public void delete(int id){
        scheduleList.remove(id);
    }

}
