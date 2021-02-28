package csc1035.project2.timetable.Person;

/**
 *
 * This is a class for representing a person object. This has methods that allow for
 * the setting and returning of variables relating to the person.
 *
 * @author Ethan Wilson
 *
 */

public class Person {

    int id;
    String firstName;
    String lastName;

    /**
     *
     * @param id This is the unique id given to a person
     * @param firstName The first name of the person
     * @param lastName The last name of the person
     */

    public Person(int id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
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

    /**
     * This returns a readable version of the person object
     * @return String representation of person
     */

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
