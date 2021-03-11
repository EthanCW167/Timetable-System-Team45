package csc1035.project2.timetable.Person;

import csc1035.project2.timetable.Module;

import javax.persistence.*;
import java.util.Set;

/**
 *
 * This class is for representing a staff object. This object class inherits its fields and methods
 * from the person class
 *
 * @author Ethan Wilson
 */
@Entity(name = "staff")
public class Staff extends Person{

    @ManyToMany() // Establish many to many relationship with modules table
    @JoinTable( //
            name = "teaches",
            joinColumns = @JoinColumn(name = "staffId"),
            inverseJoinColumns = @JoinColumn(name = "moduleId")
    )
    Set<Module> teaches;

    /**
     *
     * @param id This is a unique id given to the staff member
     * @param firstName This is the first name of the staff member
     * @param lastName This is the last name of the staff member
     */

    public Staff(String id, String firstName, String lastName){
        super(id,firstName,lastName);
    }

    public Staff(){}

}
