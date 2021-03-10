package csc1035.project2.CLI;

import java.util.Scanner;

/**
 * Command line interface for the app
 * @author Titas Janusonis
 */
public class CLI {
    static boolean exit;
    static Scanner scan;

    public CLI(){
        scan =  new Scanner(System.in);
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
            int option = getInput(1, 3);

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
                bookingHandler();
                break;
            case 2:
                timetablingHandler();
                break;
            case 3:
                exitHandler();
                break;
        }
    }

    private static void bookingHandler() {

    }

    private static void timetablingHandler() {

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

    /**
     * Handles input of integers
     * @param rangeLower lower range for the input
     * @param rangeUpper upper range for the input
     * @return provided number between rangeLower and rangeUpper
     */
    public static int getInput(int rangeLower, int rangeUpper) {
        int input;
        while (true) {

            if(scan.hasNextInt()){
                input = scan.nextInt();
                if (input < rangeLower || input > rangeUpper) {
                    System.out.println("Enter a number from " +
                            rangeLower + " to " + rangeUpper);
                    scan.nextLine();
                }else {
                    break;
                }

            }else{
                System.out.println("Enter a number from " +
                        rangeLower + " to " + rangeUpper);
                scan.nextLine();
            }
        }
        return input;
    }

}
