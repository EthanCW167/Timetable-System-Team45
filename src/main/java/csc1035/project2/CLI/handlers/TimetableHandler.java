package csc1035.project2.CLI.handlers;

import csc1035.project2.util.Controller;
import csc1035.project2.util.Helpers;
import csc1035.project2.timetable.Timetabling;
import csc1035.project2.timetable.Module;
import csc1035.project2.timetable.ModuleRequirements;
import csc1035.project2.util.IController;
import java.util.Scanner;

import static csc1035.project2.timetable.Timetabling.*;


/**
 * Class used for handling timetabling actions
 * with CLI
 *
 * @author Titas Janusonis
 */
public class TimetableHandler {

    private static boolean exit;
    private static IController c;
    private static Timetabling timetable;

    public TimetableHandler(){
        c = new Controller();


        exit = false;
    }

    public void run(){
        System.out.println("Welcome to the timetabling system");

        while (!exit){
            printMenu();
            int option = Helpers.getInput(1, 5);

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
        System.out.println("Input module number please");
        Module module = Helpers.getModuleInput(c.readAll("modules"));

        System.out.println("List of students taking module: ");
        System.out.println(studentsTakeModule(module.getId()));

        System.out.println("List of module teachers: ");
        System.out.println(teacherModule(module.getId()));

        System.out.println("Module requirements: ");
        System.out.println(moduleRequirements());







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
