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

/**
 * ScheduleFactory handles all the functions required to generate a schedule using-
 * -parameters passed in by a StudentPreferences object.
 *
 * @version 1.2
 */
@Service
public class ScheduleFactory {

    private final CourseMapper courseMapper;

    /**
     * instantiates a Graph object to be filled with Courses later
     * @param courseMapper CourseMapper object
     */
    public ScheduleFactory(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    /**
     * generates a schedule
     * @param studentPreferences int courses per quarter and boolean summer availability
     * @param courseList list of courses and prerequisites, represented by a Graph object
     * @return a nested List of Courses per Quarter
     */
    private Schedule generateSchedule(StudentPreferences studentPreferences, List<Course> courseList) {

        List<List<Course>> parsedList = buildScheduleFromList(courseList, studentPreferences.getCoursesPerQuarter());
        Schedule schedule = new Schedule(new ArrayList<>());
        Season currentSeason = studentPreferences.getStartingSeason();

        for (int i = 0; i < parsedList.size(); i++) {

            //prevent courses from being added to summer quarter if preferences set to false
            if(!studentPreferences.getSummerQuarter() && currentSeason.equals(Season.SUMMER)){
                currentSeason = seasonCycler(currentSeason);
            }
            // ADD a new Quarter object with that season
            schedule.getQuarterList().add(new Quarter(currentSeason, new Course[studentPreferences.getCoursesPerQuarter()]));
            for (int j = 0; j < parsedList.get(i).size(); j++) {
                //add course to schedule
                schedule.getQuarterList().get(i).getCourseList()[j] = parsedList.get(i).get(j);
            }
            // Increment SEASON to the next SEASON
            currentSeason = seasonCycler(currentSeason);
        }

        return schedule;
    }

    /**
     * build schedule using sorted Courses
     * @param preferences StudentPreferences
     * @param courseList list of courses and prerequisites, represented by a Graph object
     * @return Graph sorted by prerequisite and grouped by student preference
     */
    public Schedule build(StudentPreferences preferences, List<Course> courseList) {
        return generateSchedule(preferences, courseMapper.build(courseList));
    }

    /**
     * cycles through Season enums
     * @param currentSeason current season
     * @return new season
     */
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

    /**
     * breaks up list into smaller chunks for use in schedule generation
     * @param inputList large List to be broken up
     * @param chunkSize how large each chunk should be. CoursesPerQuarter parameter in StudentPreferences object
     * @return a list of nested lists to be sorted
     */
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
