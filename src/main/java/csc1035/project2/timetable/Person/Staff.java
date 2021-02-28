package csc1035.project2.timetable.Person;

public class Staff extends Person{

    public Staff(int id, String firstName, String lastName){
        super(id,firstName,lastName);
    }

    public void returnStaffData(){

    }

    public static void main(String[] args) {

        Staff p = new Staff(34597, "Ethan", "Wilson");

        System.out.println(p);;
    }
}
