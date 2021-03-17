package csc1035.project2.timetable;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.Room;
import csc1035.project2.booking.RoomBooking;
import csc1035.project2.util.Controller;
import csc1035.project2.util.IController;
import org.hibernate.Session;


import java.time.*;
import java.util.List;


public class TimetableGenerator {

    RoomBooking roomBooker;
    IController controller;
    Session s;
    LocalDate startDate;
    LocalTime startOfDayTime;
    LocalTime endOfDayTime;
    LocalDate currentDate;
    LocalTime currentTime;


    public TimetableGenerator()
    {
        s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        controller = new Controller();
        roomBooker = new RoomBooking(controller.readAll("room"));
        startDate = LocalDate.now();
        startOfDayTime = LocalTime.of(9,0);
        endOfDayTime = LocalTime.of(15,30);


    }
    public boolean generateTimetable()
    {
        List<Module> modules = controller.readAll("modules");

        for (Module m : modules)
        {
            //moduleCapacity = SELECT FROM takes t count (distinct(t.studentID)) WHERE t.moduleID = :moduleID
            int moduleCapacity = s.createQuery("select S from students S join S.takes T where T.id = :id").setParameter("id",m.getId()).list().size();
            controller.readAll("takes");

            //Repeat below for practicals and lectures
            int lectureLength = 2; //Read length from module requirements

            currentDate = startDate;

            Room bookedRoom = null;
            while (bookedRoom == null)
            {
                bookedRoom = tryBookRoom(moduleCapacity,lectureLength);
                setCurrentTime(30);

            }
            roomBooker.reserveRoom(bookedRoom,m,LocalDateTime.of(currentDate, currentTime),LocalDateTime.of(currentDate, currentTime.plusHours(lectureLength)));

        }
        return true;
    }
    private void setCurrentTime(int minsAdded)
    {
        currentTime.plusMinutes(minsAdded);
        if (currentTime.isAfter(endOfDayTime))
        {
            currentTime = startOfDayTime;
            currentDate.plusDays(1);
            //Needs to be stopped if it goes past 1 week
        }

    }
    private Room tryBookRoom(int minCapacity, int sessionLength)
    {
        LocalDateTime attemptedTime = LocalDateTime.of(startDate,currentTime);
        List<Room> availableRooms = roomBooker.findAvailableRooms(attemptedTime,sessionLength,minCapacity);
        if (availableRooms != null) //This means there is a bookable room
        {
            return availableRooms.get(0);

        }

        return null;
    }


}
