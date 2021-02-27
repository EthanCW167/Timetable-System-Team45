package csc1035.project2.booking;

import csc1035.project2.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class TestingRoomClass {
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
