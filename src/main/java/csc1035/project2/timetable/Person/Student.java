package csc1035.project2.timetable.Person;

import javax.persistence.*;

/**
 * This is a class for representing a Student. This class inherits the fields and methods
 * found in the person class
 *
 * @author Ethan Wilson
 *
 */
@Entity(name = "students")
public class Student extends Person{

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

    @Override
    public void assignRoom(){

    }
}
