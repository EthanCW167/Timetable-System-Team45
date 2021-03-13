package csc1035.project2.booking.reservation;

import csc1035.project2.HibernateUtil;
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

        IController c = new Controller();

        List l =  c.readAll( "reservations");

        System.out.println(l);
    }
}
