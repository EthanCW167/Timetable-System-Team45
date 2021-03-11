package csc1035.project2.timetable;

import csc1035.project2.timetable.Person.Student;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "modules")
public class Module {

    @Id
    @Column(updatable = false, nullable = false)
    private String id;

    @Column
    private String name;

    @Column
    private int credits;

    @Column
    private int weeks;

    @ManyToMany(mappedBy = "takes")
    Set<Student> takenByStudent;

    public Module(String name, int credits, int weeks) {
        this.name = name;
        this.credits = credits;
        this.weeks = weeks;
    }

    public Module() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }
}
