package edu.greenriver.sdev.adviseit.apis;

import edu.greenriver.sdev.adviseit.model.entities.Schedule;
import edu.greenriver.sdev.adviseit.services.factories.ScheduleFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleAPI {

    private ScheduleFactory scheduleFactory;

    public ScheduleAPI(ScheduleFactory scheduleFactory) {
        this.scheduleFactory = scheduleFactory;
    }

    @GetMapping("/build")
    public Schedule buildSchedule() {
        scheduleFactory.populateSchedule();
        return scheduleFactory.build();
    }
}
