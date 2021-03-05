package csc1035.project2.booking.reservation;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.Room;
import csc1035.project2.timetable.Person.Student;
import csc1035.project2.util.Controller;
import csc1035.project2.util.IController;
import org.hibernate.Session;

import java.util.List;

/**
 * Test class for reservations
 *
 * @author Titas Janusonis
 */
public class ReservationTest {

    public static void main(String[] args) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        // Read
        session.beginTransaction();

        List reservations = session.createQuery("from reservations ").list();

        for (Reservation res : (Iterable< Reservation >) reservations) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Room number: " + res.getRoomNumber());
            System.out.println("Module Id : " + res.getModuleId());
        }
        session.getTransaction().commit();
        session.close();

//        IController c = new Controller();
//        Reservation r = new Reservation();
//
//        List<Reservation> l =  c.getAll( Reservation.class);
//
//        for (Reservation i: l ){
//            System.out.println(i.getRoomNumber());
//        }
    }
}
