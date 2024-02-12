package edu.greenriver.sdev.adviseit.services.factories;

import edu.greenriver.sdev.adviseit.model.entities.Course;
import edu.greenriver.sdev.adviseit.model.entities.Quarter;
import edu.greenriver.sdev.adviseit.model.entities.Schedule;
import edu.greenriver.sdev.adviseit.model.entities.StudentPreferences;
import edu.greenriver.sdev.adviseit.model.enums.Season;
import edu.greenriver.sdev.adviseit.services.StudentPreferencesService;
import edu.greenriver.sdev.adviseit.services.mappers.CourseMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleFactory {

    private final CourseMapper courseMapper;

    public ScheduleFactory(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    private Schedule generateSchedule(StudentPreferences studentPreferences, List<Course> courseList) {

        List<List<Course>> parsedList = buildScheduleFromList(courseList, studentPreferences.getCoursesPerQuarter());

        // int totalNumberOfQuarters = (int) Math.ceil((double) parsedList.size() / (double) studentPreferences.getCoursesPerQuarter());
        Schedule schedule = new Schedule(new ArrayList<>());

        //Season currentSeason = Season.FALL;
        Season currentSeason = studentPreferences.getStartingSeason();
        for (int i = 0; i < parsedList.size(); i++) {

            // ADD a new Quarter object with that season
            schedule.getQuarterList().add(new Quarter(currentSeason, new Course[studentPreferences.getCoursesPerQuarter()]));
            for (int j = 0; j < parsedList.get(i).size(); j++) {
                schedule.getQuarterList().get(i).getCourseList()[j] = parsedList.get(i).get(j);
            }

            // Increment SEASON to the next SEASON
            currentSeason = seasonCycler(currentSeason);
        }

        return schedule;
    }

    public Schedule build(StudentPreferences preferences, List<Course> courseList) {
        return generateSchedule(preferences, courseMapper.build(courseList));
    }

    private Season seasonCycler(Season currentSeason) {

        Season nextSeason;

        switch (currentSeason) {
            case FALL:
                nextSeason = Season.WINTER;
                break;
            case WINTER:
                nextSeason = Season.SPRING;
                break;
            case SPRING:
                nextSeason = Season.SUMMER;
                break;
            case SUMMER:
                nextSeason = Season.FALL;
                break;
            default:
                nextSeason = Season.FALL;
                break;
        }

        return nextSeason;
    }

    private List<List<Course>> buildScheduleFromList(List<Course> inputList, int chunkSize) {

        List<List<Course>> result = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i += chunkSize) {
            int end = Math.min(inputList.size(), i + chunkSize);
            List<Course> chunk = new ArrayList<>(inputList.subList(i, end));
            result.add(chunk);
        }

        return result;
    }
}
