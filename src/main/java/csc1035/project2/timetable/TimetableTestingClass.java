package csc1035.project2.timetable;

import static csc1035.project2.timetable.Timetabling.*;

public class TimetableTestingClass {

    public static void main(String[] args) {

        // StudentTakeModules method tests

        // test: return all students that take the module with id "NTT2305"
        // expected outcome:
        /*
        ----------------------
        ID: 212455073
        First Name: Leandra
        Last Name: Vinson
        ----------------------
        ID: 212919325
        First Name: Sheri
        Last Name: Turbard
         */
        System.out.println(studentsTakeModule("NTT2305"));

        // test: return all students that take the module with id ""
        // expected outcome:
        /*
            Empty Array []
        */
        System.out.println(studentsTakeModule(""));

        // teacherModule method tests
        // test: return all teachers that take the module with id "BKR9132"
        // expected outcome:
        /*
        ----------------------
        ID: NUC5633757
        First Name: Denice
        Last Name: De Gowe
        */
        System.out.println(teacherModule("BKR9132"));

        // test: return all teachers that take the module with id ""
        // expected outcome:
        /*
            Empty Array []
        */
        System.out.println(teacherModule(""));

        // moduleRequirements method test
        // test: return the module requirements of all modules
        // expected outcome:
        /*
        All module requirements following the structure:

        ID
        Week Commencing
        Lectures Per Week
        Lecture Length
        Practicals Per Week
        Practical Length

        number of modules returned: 10
         */
        System.out.println(moduleRequirements());

        // specificModuleRequirements method test
        // test: return the module requirements of the module with id "BKR9132"
        // expected outcome:
        /*
        -------------------------------
        ID: 'BKR9132
        Week Commencing: '10/01/22
        Lectures Per Week: 4
        Lecture Length: 2
        Practicals Per Week: 1
        Practical Length: 1
         */
        System.out.println(specificModuleRequirements("BKR9132"));

        // test: return the module requirements of the module with id "BKR9132"
        // expected outcome:
        /*
            Empty Array []
         */
        System.out.println(specificModuleRequirements(""));
    }

}
