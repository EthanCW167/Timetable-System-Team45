package csc1035.project2.CLI.handlers;

import csc1035.project2.util.Helpers;

/**
 * Class used for handling timetabling actions
 * with CLI
 *
 * @author Titas Janusonis
 */
public class TimetableHandler {

    private static boolean exit;

    public TimetableHandler(){
        exit = false;
    }
    public void run(){
        System.out.println("Welcome to the timetabling system");

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
                infoHandler();
                break;
            case 2:
                timetableHandler("normal");
                break;
            case 3:
                timetableHandler("sociallyDistant");
                break;
            case 4:
                personTimetableHandler();
                break;
            case 5:
                exitHandler();
                break;
        }
    }

    private static void infoHandler() {
        // Prints module info (students, staff, module requirements)
    }

    private static void timetableHandler(String type) {
        // Prints timetable for the school
    }

    private static void personTimetableHandler() {
        // Prints persons timetable
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
        System.out.println("1 --- Info about a module");
        System.out.println("2 --- Create school's timetable (normal)");
        System.out.println("3 --- Create school's timetable (socially distant)");
        System.out.println("4 --- Create person's timetable");
        System.out.println("5 --- Return");
    }
}
