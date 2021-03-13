package csc1035.project2.util;

import csc1035.project2.booking.Room;
import csc1035.project2.timetable.Module;

import java.util.List;
import java.util.Scanner;

/**
 * Class with helper methods
 *
 * @author Titas
 */
public class Helpers {
    /**
     * Handles input of integers
     * @param rangeLower lower range for the input
     * @param rangeUpper upper range for the input
     * @return provided number between rangeLower and rangeUpper
     */
    public static int getInput(int rangeLower, int rangeUpper) {
        int input;
        Scanner scan =  new Scanner(System.in);

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

    /**
     * Handles input for getting a room
     */
    public static Room getRoomInput(List<Room> rooms){
        Room selectedRoom = null;
        String input = null;
        Scanner scan =  new Scanner(System.in);
        IController c = new Controller();

        while (true) {

            if(scan.hasNext("[0-9]+\\.[0-9]+")){
                input = scan.next();
                try{
                    selectedRoom =  (Room) c.readById(Room.class, input);
                    break;
                }
                catch (Exception e){
                    System.out.println("Room not found");
                }

            }else{
                System.out.println("Enter a valid room number");
                scan.nextLine();
            }
        }
        return selectedRoom;
    }

    /**
     * Handles input for getting a module
     */
    public static Module getModuleInput(List<Module> module){
        Module selectedModule = null;
        String input = null;
        Scanner scan =  new Scanner(System.in);
        IController c = new Controller();

        while (true) {

            if(scan.hasNext("[A-Z0-9]+")){
                input = scan.next();
                try {
                    selectedModule = (Module) c.readById(Module.class, input);
                    break;
                }
                catch (Exception e){
                    System.out.println("Module not found");
                }

            }else{
                System.out.println("Enter a valid module number");
                scan.nextLine();
            }
        }

        return selectedModule;
    }

}
