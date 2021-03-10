package csc1035.project2.util;

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

}
