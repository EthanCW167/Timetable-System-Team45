package csc1035.project2.testing;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.Room;
import org.hibernate.Session;

import java.util.List;

/**
 * This is a class to test that the Room class relates properly to the "rooms" table in the database
 *
 * @author Dillon Reed
 */
public class TestingRoomClass {

    /**
     * Tests that records can be read correctly from the "rooms" table. Prints the records out to the console
     */
    private static void testReadRooms(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Read
        session.beginTransaction();

        List rooms = session.createQuery("from rooms ").list();

        for (Room room : (Iterable<Room>) rooms) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Type : " + room.getType());
            System.out.println("Max Capacity : " + room.getMaxCapacity());
            System.out.println("Social Distancing Capacity : " + room.getSocialDistancingCapacity());
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void main(String[] args) {
        testReadRooms();
    }
}
