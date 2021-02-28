package csc1035.project2.timetable.Person;

/**
 *
 * This class is for representing a staff object. This object class inherits its fields and methods
 * from the person class
 *
 * @author Ethan Wilson
 */

public class Staff extends Person{

    /**
     *
     * @param id This is a unique id given to the staff member
     * @param firstName This is the first name of the staff member
     * @param lastName This is the last name of the staff member
     */

    public Staff(int id, String firstName, String lastName){
        super(id,firstName,lastName);
    }

    public void returnStaffData(){

    }

}
