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
    private Schedule schedule;
    private StudentPreferences studentPreferences;

    public ScheduleFactory(CourseMapper courseMapper, StudentPreferencesService studentPreferencesService) {
        this.courseMapper = courseMapper;
        this.schedule = new Schedule(new ArrayList<>());
        this.studentPreferences = new StudentPreferences(3, true);
    }

    private List<Course> buildCourseMap() {
        courseMapper.loadCourses();
        courseMapper.loadCourseDependencies();
        return courseMapper.sortAndExportCourses();
    }

    public void populateSchedule() {

        List<List<Course>> parsedList = buildScheduleFromList(buildCourseMap(), studentPreferences.getCoursesPerQuarter());

        // int totalNumberOfQuarters = (int) Math.ceil((double) parsedList.size() / (double) studentPreferences.getCoursesPerQuarter());

        Season currentSeason = Season.FALL;
        for (int i = 0; i < parsedList.size(); i++) {

            // ADD a new Quarter object with that season
            schedule.getQuarterList().add(new Quarter(currentSeason, new Course[studentPreferences.getCoursesPerQuarter()]));
            for (int j = 0; j < parsedList.get(i).size(); j++) {
                schedule.getQuarterList().get(i).getCourseList()[j] = parsedList.get(i).get(j);
            }

            // Increment SEASON to the next SEASON
            currentSeason = seasonCycler(currentSeason);
        }
    }

    public Schedule build() {
        return schedule;
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
            case SUMMER:
                nextSeason = Season.FALL;
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
