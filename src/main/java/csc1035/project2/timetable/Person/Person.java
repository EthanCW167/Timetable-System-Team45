package csc1035.project2.timetable.Person;

import javax.persistence.*;
/**
 *
 * This is a class for representing a person object. This has methods that allow for
 * the setting and returning of variables relating to the person.
 *
 * @author Ethan Wilson
 *
 */

@MappedSuperclass
public class Person {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    /**
     *
     * @param id This is the unique id given to a person
     * @param firstName The first name of the person
     * @param lastName The last name of the person
     */

    public Person(String id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Person(){

    }

    public String getID(){
        return id;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void assignRoom(){

    }

    /**
     * This returns a readable version of the person object
     * @return String representation of person
     */

    @Override
    public String toString() {
        return "----------------------" + '\n' +
                "ID: " + id + '\n' +
                "First Name: " + firstName + '\n' +
                "Last Name: " + lastName + '\n';
    }
}
