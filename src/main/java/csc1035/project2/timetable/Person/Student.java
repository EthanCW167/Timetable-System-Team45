package csc1035.project2.timetable.Person;

/**
 * This is a class for representing a Student. This class inherits the fields and methods
 * found in the person class
 *
 * @author Ethan Wilson
 *
 */

public class Student extends Person{

    /**
     *
     * @param id This is a unique id given to a student
     * @param firstName First name of the student
     * @param lastName Last name of the student
     */
    public Student(int id, String firstName, String lastName){

        super(id,firstName,lastName);
    }
    public void returnStudentData(){

    }
}
