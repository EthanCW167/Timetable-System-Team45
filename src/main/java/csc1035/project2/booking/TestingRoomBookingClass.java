package csc1035.project2.booking;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.reservation.Reservations;
import csc1035.project2.timetable.Module;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * A class that ensures that the RoomBooking class works as expected
 *
 * @author Dillon Reed
 */
public class TestingRoomBookingClass {
    /**
     * This is a method to return all of the rooms in the rooms table in the database
     * @return A list of all rooms
     */
    public List<Room> getRooms(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Read
        session.beginTransaction();

        List rooms = session.createQuery("from rooms ").list();

        session.getTransaction().commit();
        session.close();

        return rooms;
    }

    /**
     * This is a method to return all of the modules in the modules table in the database
     * @return A list of all modules
     */
    public List<Module> getModules(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Read
        session.beginTransaction();

        List modules = session.createQuery("from modules").list();

        session.getTransaction().commit();
        session.close();

        return modules;
    }

    /**
     * This is a method to return all of the reservations in the reservations table in the database
     * @return A list of all reservations
     */
    public List<Reservations> getReservations(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Read
        session.beginTransaction();

        List reservations = session.createQuery("from reservations ").list();

        session.getTransaction().commit();
        session.close();

        return reservations;
    }

    private RoomBooking roomBooker = new RoomBooking(getRooms());
    private Random randomNumber = new Random();

    /**
     * This is a method that tests that the reserveRoom method in RoomBooking works as expected
     */
    public void testReserveRoom(){
        // Picks a random room and module from the database
        Room room = getRooms().get(randomNumber.nextInt(getRooms().size()));
        Module module = getModules().get(randomNumber.nextInt(getModules().size()));

        // Prints the number of reservations before and then after the reservation should be made
        System.out.println(getReservations().size());
        boolean test = roomBooker.reserveRoom(room, module, LocalDateTime.now(), LocalDateTime.of(2021, 10, 13, 15, 10));
        System.out.println("Reservation made : " + test);
        System.out.println(getReservations().size());
    }

    /**
     * This is a method that tests that the cancelRoom method in RoomBooking works as expected
     */
    public void testCancelRoom(){
        // Picks a random room and module from the database
        Room room = getRooms().get(randomNumber.nextInt(getRooms().size()));
        Module module = getModules().get(randomNumber.nextInt(getModules().size()));

        // Prints the number of reservations before and then after the reservation should be deleted
        System.out.println(getReservations().size());
        boolean test = roomBooker.cancelRoom(room, module, LocalDateTime.now(), LocalDateTime.of(2001, 10, 13, 15, 10));
        System.out.println("Reservation deleted : " + test);
        System.out.println(getReservations().size());
    }

    /**
     * This is a method that tests that the findAvailableRooms method in RoomBooking works as expected
     */
    public void testFindAvailableRooms(){
        // Fills the roomList with the rooms from the database
        roomBooker.setRoomList(getRooms());

        // Print out the available rooms for given parameters
        List<Room> availableRooms = roomBooker.findAvailableRooms(LocalDateTime.now(), 60, 30);
        for (Room room :availableRooms) {
            System.out.println(room);
        }
    }

    /**
     * This is a method that tests that the createRoomTimetable method in RoomBooking works as expected
     */
    public void testCreateRoomTimetable(){
        // Finds a random room from the database
        Room room = getRooms().get(randomNumber.nextInt(getRooms().size()));

        // Prints out the timetable for the room
        List<Reservations> reservationsList = roomBooker.createRoomTimetable(room);
        for (Reservations reservation:reservationsList) {
            System.out.println(reservation);
        }
    }

    /**
     * This is a method that tests that the updateRoomInfo method in RoomBooking works as expected
     */
    public void testUpdateRoomInfo(){
        // Finds a random room from the database
        int randomIndex = randomNumber.nextInt(getRooms().size()-1);
        Room room = getRooms().get(randomIndex);

        // Prints out the rooms at index: x, x-1, x+1
        System.out.println(getRooms().get(randomIndex));
        System.out.println(getRooms().get(randomIndex-1));
        System.out.println(getRooms().get(randomIndex+1));

        // Updates the room at index x with x+1
        Room newRoom = getRooms().get(randomIndex+1);
        newRoom.setRoomNumber(room.getRoomNumber());

        boolean roomChanged = roomBooker.updateRoomInfo(room.getRoomNumber(), newRoom);
        System.out.println(roomChanged);

        // Prints out the rooms at index: x, x-1, x+1 again to show difference
        System.out.println(getRooms().get(randomIndex));
        System.out.println(getRooms().get(randomIndex-1));
        System.out.println(getRooms().get(randomIndex+1));
    }

    public static void main(String[] args) {
        TestingRoomBookingClass tester = new TestingRoomBookingClass();

        tester.testReserveRoom();
//        tester.testCancelRoom();
//        tester.testFindAvailableRooms();
//        tester.testCreateRoomTimetable();
//        tester.testUpdateRoomInfo();
    }
}
