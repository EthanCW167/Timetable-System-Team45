package csc1035.project2.CLI;

import csc1035.project2.CLI.handlers.*;
import csc1035.project2.util.Helpers;

/**
 * Command line interface for the app
 * @author Titas Janusonis
 */
public class CLI {
    static boolean exit;

    public CLI(){
        exit = false;

    }

    public static void main(String[] args) {

        CLI app = new CLI();
        app.run();

    }

    public void run(){
        System.out.println("CLI for ");

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
                BookingHandler BH = new BookingHandler();
                BH.run();
                break;
            case 2:
                TimetableHandler TH = new TimetableHandler();
                TH.run();
                break;
            case 3:
                exitHandler();
                break;
        }
    }


    /**
     * Exits the IO loop
     */
    private static void exitHandler(){
        System.out.println("Exiting...");
        exit = true;
    }

    public static void printMenu(){
        System.out.println("Choose an option:");
        System.out.println("1 --- Booking system");
        System.out.println("2 --- Timetabling system");
        System.out.println("3 --- Exit");
    }


}
