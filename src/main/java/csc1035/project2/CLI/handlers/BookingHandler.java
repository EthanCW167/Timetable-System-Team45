package csc1035.project2.CLI.handlers;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.Room;
import csc1035.project2.booking.RoomBooking;
import csc1035.project2.timetable.Module;
import csc1035.project2.util.Controller;
import csc1035.project2.util.Helpers;
import csc1035.project2.util.IController;
import org.hibernate.Session;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Class used for handling booking actions
 * with CLI
 *
 * @author Titas Janusonis
 */
public class BookingHandler {

    private static boolean exit;
    private static IController c;
    private static RoomBooking booking;
    
    public BookingHandler(){
        c = new Controller();
        booking = new RoomBooking(c.readAll( "rooms"));
        exit = false;
    }
    public void run(){
        System.out.println("Welcome to the booking system");
        
        while (!exit){
            printMenu();
            int option = Helpers.getInput(1, 7);

            optionManager(option);
            System.out.println("-----------------");
        }
    }

    /**
     * Manages the input for given option
     * @param option mapping with the action
     */
    public static void optionManager(int option){
        switch (option){
            case 1:
                roomListHandler();
                break;
            case 2:
                reservationHandler();
                break;
            case 3:
                cancellationHandler();
                break;
            case 4:
                findRoomsHandler();
                break;
            case 5:
                roomTimetableHandler();
                break;
            case 6:
                updateRoomHandler();
                break;
            case 7:
                exitHandler();
                break;
        }
    }

    /**
     * Prints all the rooms in the university
     */
    private static void roomListHandler() {

        List<Room> rooms =  booking.getRoomList();

        for (Room room : rooms) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Type : " + room.getType());
            System.out.println("Max Capacity : " + room.getMaxCapacity());
            System.out.println("Social Distancing Capacity : " + room.getSocialDistancingCapacity());
        }
    }

    private static void reservationHandler() {

        System.out.println("Write the room number:");
        Room room = Helpers.getRoomInput(booking.getRoomList());

        System.out.println("Write the module number:");
        Module module = Helpers.getModuleInput(c.readAll("modules"));

        System.out.println("Write the start time:");
        LocalDateTime from = Helpers.getTimeInput();

        System.out.println("Write the end time:");
        LocalDateTime to = Helpers.getTimeInput();

        if (booking.reserveRoom(room, module, from, to)){
            System.out.println("Room reserved");
        }
        else {
            System.out.println("Room reservation failed, please try again.");
        }

    }

    private static void cancellationHandler() {
        // Lets to cancel a room
        // Booking.cancelRoom()
        // Make selection CLI
    }

    /**
     * Prints all the available rooms for the given parameters
     */
    private static void findRoomsHandler() {
        System.out.println("Enter time for the look up");
        LocalDateTime timeStamp = Helpers.getTimeInput();

        System.out.println("Length of the reservation in minutes:");
        int forLength = Helpers.getInput(1, 360);

        System.out.println("Capacity of students wanted:");
        int forCapacity = Helpers.getInput(1, 1000);

        List<Room> rooms = booking.findAvailableRooms(timeStamp, forLength, forCapacity);

        for (Room room : rooms) {
            System.out.println("---------------------------------------------------------");
            System.out.println("Room Number : " + room.getRoomNumber());
            System.out.println("Type : " + room.getType());
            System.out.println("Max Capacity : " + room.getMaxCapacity());
            System.out.println("Social Distancing Capacity : " + room.getSocialDistancingCapacity());
        }

    }

    private static void roomTimetableHandler() {
        // Prints timetable of a room
        // Booking.createRoomTimetable()
    }

    private static void updateRoomHandler() {
        // Lets to update a room
        // Booking.updateRoomInfo()
    }

    /**
     * Exits the IO loop
     */
    private static void exitHandler(){
        System.out.println("Returning...");
        exit = true;
    }

    private void printMenu() {
        System.out.println("Choose an option:");
        System.out.println("1 --- Print the list of rooms");
        System.out.println("2 --- Reserve a room");
        System.out.println("3 --- Cancel reserved room");
        System.out.println("4 --- Find available rooms");
        System.out.println("5 --- Create a timetable for a room");
        System.out.println("6 --- Update room info");
        System.out.println("7 --- Return");
    }
}
