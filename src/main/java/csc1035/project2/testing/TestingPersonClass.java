package csc1035.project2.testing;

import csc1035.project2.HibernateUtil;
import csc1035.project2.timetable.Person.Student;
import org.hibernate.Session;

import java.util.List;

/**
 * This is a class to test that the Room class relates properly to the students" table in the database,
 * code has been temporarily reused from TestingRoomClass as it fulfils the same purpose.
 *
 * @author Dillon Reed
 */
public class TestingPersonClass {

    /**
     * Tests that records can be read correctly from the "students" table. Prints the records out to the console
     */
    private static void testReadRooms(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Read
        session.beginTransaction();

        List students = session.createQuery("from students ").list();

        System.out.println(students);

        for (Student student : (Iterable<Student>) students) {
            System.out.println("---------------------------------------------------------");
            System.out.println("ID : " + student.getID());
            System.out.println("First name : " + student.getFirstName());
            System.out.println("Last name : " + student.getLastName());
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        testReadRooms();
    }
}
