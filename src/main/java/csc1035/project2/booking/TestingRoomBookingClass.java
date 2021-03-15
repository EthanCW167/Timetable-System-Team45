package csc1035.project2.booking;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.reservation.Reservations;
import csc1035.project2.timetable.Module;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        System.out.println("There are : " + getReservations().size() + " Reservations");
        boolean test = roomBooker.reserveRoom(room, module, LocalDateTime.now(), LocalDateTime.of(2021, 3, 15, 15, 10));
        System.out.println("Reservation made : " + test);
        System.out.println("There are : " + getReservations().size() + " Reservations");
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
        System.out.println("Available Rooms : ");
        for (Room room :availableRooms) {
            System.out.println(room.getRoomNumber());
        }
    }

    /**
     * This is a method that tests that the createRoomTimetable method in RoomBooking works as expected
     */
    public void testCreateRoomTimetable(){
        // Finds a random room from the database
        List<Reservations> reservationsList = new ArrayList<>();

        boolean found = false;
        while (!found) {
            Room room = getRooms().get(randomNumber.nextInt(getRooms().size()));
            reservationsList = roomBooker.createRoomTimetable(room);
            if (reservationsList.size() > 0){
                found = true;
            }
        }

        // Prints out the timetable for the room
        for (Reservations reservation:reservationsList) {
            System.out.println("\nReservation :");
            System.out.println("Room Number : " + reservation.getRoomNumber());
            System.out.println("Module ID : " + reservation.getModuleId());
            System.out.println("From date : " + reservation.getFrom());
            System.out.println("To date : " + reservation.getTo());
        }
    }

    /**
     * This is a method that tests that the updateRoomInfo method in RoomBooking works as expected
     */
    public void testUpdateRoomInfo(){
        // Finds a random room from the database
        int randomIndex = randomNumber.nextInt(getRooms().size()-1);
        List<Room> rooms = getRooms();
        Room room = rooms.get(randomIndex);

        // Prints out the room's info
        System.out.println("\n------------- Room to Updated ------------");
        System.out.println("Room Number : " + room.getRoomNumber());
        System.out.println("Max capacity : " + room.getMaxCapacity());
        System.out.println("Social Distancing capacity : " + room.getSocialDistancingCapacity());
        System.out.println("Type : " + room.getType());

        // Updates the room with a random room's info
        Room newRoom = rooms.get(randomIndex+1);
        newRoom.setRoomNumber(room.getRoomNumber());

        // Prints the newRoom's info
        System.out.println("\n------------ Info to be put in --------------");
        System.out.println("Room Number : " + newRoom.getRoomNumber());
        System.out.println("Max capacity : " + newRoom.getMaxCapacity());
        System.out.println("Social Distancing capacity : " + newRoom.getSocialDistancingCapacity());
        System.out.println("Type : " + newRoom.getType());

        boolean roomChanged = roomBooker.updateRoomInfo(room.getRoomNumber(), newRoom);
        System.out.println("Was the room updated successfully : " + roomChanged);

        // Updates the rooms list
        rooms = getRooms();
        room = rooms.get(randomIndex);

        // Prints out the room's info again so you can see that it was updated correctly
        System.out.println("\n------------- Room that was Updated ------------");
        System.out.println("Room Number : " + room.getRoomNumber());
        System.out.println("Max capacity : " + room.getMaxCapacity());
        System.out.println("Social Distancing capacity : " + room.getSocialDistancingCapacity());
        System.out.println("Type : " + room.getType());
    }

    public static void main(String[] args) {
        TestingRoomBookingClass tester = new TestingRoomBookingClass();

        //tester.testReserveRoom();
        //tester.testCancelRoom();
        //tester.testFindAvailableRooms(); // Working
        //tester.testCreateRoomTimetable(); // Working
        tester.testUpdateRoomInfo(); // Working
    }
}
