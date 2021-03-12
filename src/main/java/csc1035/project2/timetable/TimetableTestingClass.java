package csc1035.project2.timetable;

import static csc1035.project2.timetable.Timetabling.*;

public class TimetableTestingClass {

    public static void main(String[] args) {
        System.out.println(studentsTakeModule("NTT2305"));

        System.out.println(teacherModule("NTT2305"));

        System.out.println(moduleRequirements());
    }
}
