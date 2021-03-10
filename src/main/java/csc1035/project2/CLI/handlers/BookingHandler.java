package csc1035.project2.CLI.handlers;

import csc1035.project2.util.Helpers;

/**
 * Class used for handling booking actions
 * with CLI
 *
 * @author Titas Janusonis
 */
public class BookingHandler {

    private static boolean exit;
    
    public BookingHandler(){
        exit = false;
    }
    public void run(){
        System.out.println("Welcome to the booking system");
        
        while (!exit){
            printMenu();
            int option = Helpers.getInput(1, 3);

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

    private static void roomListHandler() {
        // Prints a list of all rooms from Booking class
        // Booking.rooms
    }

    private static void reservationHandler() {
        // Lets to reserve a room
        // Booking.reserveRoom()
    }

    private static void cancellationHandler() {
        // Lets to cancel a room
        // Booking.cancelRoom()
    }

    private static void findRoomsHandler() {
        // Prints all rooms available
        // Booking.findAvailableRooms()
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
