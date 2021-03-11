package csc1035.project2.timetable.Person;

import csc1035.project2.timetable.Module;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This is a class for representing a Student. This class inherits the fields and methods
 * found in the person class
 *
 * @author Ethan Wilson
 *
 */
@Entity(name = "students")
public class Student extends Person{

    @ManyToMany() // Establish many to many relationship with modules table
    @JoinTable( //
            name = "takes",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "moduleId")
    )
    Set<Module> takes;
    /**
     *
     * @param id This is a unique id given to a student
     * @param firstName First name of the student
     * @param lastName Last name of the student
     */
    public Student(String id, String firstName, String lastName){

        super(id,firstName,lastName);
    }
    public Student(){}

}
