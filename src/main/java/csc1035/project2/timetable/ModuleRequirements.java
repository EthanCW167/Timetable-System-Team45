package csc1035.project2.timetable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * This is a class that represents the Module Requirements database table, it has methods for
 * the setting and returning of fields relating to the requirements of the module
 *
 * @author
 */
@Entity(name = "moduleRequirements")
public class ModuleRequirements {

    @Id
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String weekCommencing;

    @Column
    private int lecturesPerWeek;

    @Column
    private int lectureLength;

    @Column
    private int practicalsPerWeek;

    @Column
    private int practicalLength;

    public ModuleRequirements(){ }

    /**
     *
     * @param id This is the unique id given to a module
     * @param weekCommencing This is the week in which the module begins
     * @param lecturesPerWeek This is the number of lectures per week
     * @param lectureLength This the length of the lectures
     * @param practicalsPerWeek This is the number of practicals per week
     * @param practicalLength This si the length of the practicals
     */
    public ModuleRequirements(String id, String weekCommencing, int lecturesPerWeek, int lectureLength, int practicalsPerWeek, int practicalLength){
        this.id = id;
        this.weekCommencing = weekCommencing;
        this.lecturesPerWeek = lecturesPerWeek;
        this.lectureLength = lectureLength;
        this.practicalsPerWeek = practicalsPerWeek;
        this.practicalLength = practicalLength;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeekCommencing() {
        return weekCommencing;
    }

    public void setWeekCommencing(String weekCommencing) {
        this.weekCommencing = weekCommencing;
    }

    public int getLecturesPerWeek() {
        return lecturesPerWeek;
    }

    public void setLecturesPerWeek(int lecturesPerWeek) {
        this.lecturesPerWeek = lecturesPerWeek;
    }

    public int getLectureLength() {
        return lectureLength;
    }

    public void setLectureLength(int lectureLength) {
        this.lectureLength = lectureLength;
    }

    public int getPracticalsPerWeek() {
        return practicalsPerWeek;
    }

    public void setPracticalsPerWeek(int practicalsPerWeek) {
        this.practicalsPerWeek = practicalsPerWeek;
    }

    public int getPracticalLength() {
        return practicalLength;
    }

    public void setPracticalLength(int practicalLength) {
        this.practicalLength = practicalLength;
    }

    /**
     * This returns a readable version of the module requirements object
     * @return String representation of module requirements
     */
    @Override
    public String toString() {
        return "-------------------------------" + '\n' +
                "ID: '" + id + '\n' +
                "Week Commencing: '" + weekCommencing + '\n' +
                "Lectures Per Week: " + lecturesPerWeek + '\n' +
                "Lecture Length: " + lectureLength + '\n' +
                "Practicals Per Week: " + practicalsPerWeek + '\n' +
                "Practical Length: " + practicalLength + '\n';
    }
}


