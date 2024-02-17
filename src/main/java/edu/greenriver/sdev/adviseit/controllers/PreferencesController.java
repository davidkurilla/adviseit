package edu.greenriver.sdev.adviseit.controllers;

import edu.greenriver.sdev.adviseit.model.entities.Schedule;
import edu.greenriver.sdev.adviseit.model.entities.StudentPreferences;
import edu.greenriver.sdev.adviseit.services.CourseService;
import edu.greenriver.sdev.adviseit.services.factories.ScheduleFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PreferencesController {

    ScheduleFactory scheduleFactory;
    CourseService courseService;

    public PreferencesController(ScheduleFactory scheduleFactory, CourseService courseService) {
        this.scheduleFactory = scheduleFactory;
        this.courseService = courseService;
    }

    @RequestMapping("/schedule-builder")
    public String showView(Model model){
        model.addAttribute("preferences", new StudentPreferences());
        return "preferences";
    }

    @PostMapping("/schedule")
    public String showScheduleView(@ModelAttribute StudentPreferences preferences, Model model) {
        Schedule schedule = scheduleFactory.build(preferences, courseService.getAll());
        model.addAttribute("preferences", preferences);
        model.addAttribute("schedule", schedule);
        return "schedule";
    }
}
