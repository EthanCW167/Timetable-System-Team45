package csc1035.project2.timetable;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.Room;
import csc1035.project2.booking.RoomBooking;
import csc1035.project2.booking.reservation.Reservations;
import csc1035.project2.timetable.Person.Person;
import csc1035.project2.util.Controller;
import csc1035.project2.util.IController;
import org.hibernate.Session;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for generating a timetable for a school
 *
 * @author Rowan Lowe
 */

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
        roomBooker = new RoomBooking(controller.readAll("rooms"));
        startDate = LocalDate.now().plusDays(1);
        startOfDayTime = LocalTime.of(9,0);
        endOfDayTime = LocalTime.of(15,30);
        currentTime = startOfDayTime;

    }

    /**
     * Gets the reservations that are applicable to the module IDs inputted
     * @param moduleIds these are the modules to search for, if empty search for all
     * @return the list of all reservations for the given modules, or if empty all reservations
     */
    public List<Reservations> getReservations(List<String> moduleIds)
    {
        List<Reservations> reservations = new ArrayList<>();
        if (moduleIds != null) {
            for (String moduleID : moduleIds) {
                reservations.addAll((List<Reservations>)(s.createQuery("select r from reservations r where  r.moduleId = :id").setParameter("id", moduleID).list()));
                //This gets all the reservations found for each module
            }
        }
        else
        {
            reservations.addAll(s.createQuery("from reservations ").list());

        }
        return reservations;

    }

    /**
     * Creates a timetable when gives the optional filters of modules and people IDs
     * @param moduleIDs the optional filter for module IDs to create the timetable for, if empty then use all modules
     * @param peopleIDs the optional filter for people IDs to create the timetable for, if empty then ignore
     * @return
     */
    public Timetable createTimetable(ArrayList<String> moduleIDs, ArrayList<String> peopleIDs)
    {
        List<String> tempModuleIDs = new ArrayList();
        if (peopleIDs != null) //Find which modules the people take
        {
            for (String personID : peopleIDs) {
                tempModuleIDs.addAll((List<String>) (s.createQuery("select m.id from modules m join m.takenByStudent S where S.id =:id")).setParameter("id", personID).list());

                //s.createQuery("select m.id from modules m join m.takenByStudent where m.id = :id").setParameter("id",personID);
                //Get the ids of modules that are found are taken by student where id = personID
            }

           return new Timetable(getReservations(tempModuleIDs));
        }
        else {


            return new Timetable(getReservations(moduleIDs));
        }
    }

    /**
     * Creates a timetable when gives the optional filters of modules and people
     * @param modules the optional filter for module to create the timetable for, if empty then use all modules
     * @param people  the optional filter for people to create the timetable for, if empty then ignore
     * @return
     */
    public Timetable createTimetable(List<Module> modules, List<Person> people)
    {
        ArrayList<String> moduleIds = new ArrayList<>();
        ArrayList<String> peopleIds = new ArrayList<>();
        for (Module m :modules)
        {
            moduleIds.add(m.getId());

        }
        for (Person p: people)
        {
            peopleIds.add(p.getID());

        }
        return createTimetable(moduleIds,peopleIds);
    }

    /**
     * Generates a full school timetable given the modules and their requirements
     * @param isSociallyDistant if the timetable is for socially distant capacities
     * @return
     */
    public boolean generateTimetable(boolean isSociallyDistant)
    {
        List<Module> modules = controller.readAll("modules");

        for (Module m : modules)
        {
            //moduleCapacity = SELECT FROM takes t count (distinct(t.studentID)) WHERE t.moduleID = :moduleID
            int moduleCapacity = s.createQuery("select S from students S join S.takes T where T.id = :id").setParameter("id",m.getId()).list().size();

            //Repeat below for practicals and lectures
            int lectureLength = 2; //Read length from module requirements

            currentDate = startDate;
            Integer lecturesPerWeek = (Integer) s.createQuery("select r.lecturesPerWeek from moduleRequirements r where r.id = :id").setParameter("id",m.getId()).list().get(0);
            for (Integer i = 0; i < lecturesPerWeek; i++) {
                Room bookedRoom = null;
                while (bookedRoom == null) {
                    bookedRoom = tryBookRoom(moduleCapacity, lectureLength, isSociallyDistant);
                    setCurrentTime(150); //This is min time between start of one class and end of another

                }
                roomBooker.reserveRoom(bookedRoom, m, LocalDateTime.of(currentDate, currentTime), LocalDateTime.of(currentDate, currentTime.plusHours(lectureLength)));
            }
        }
        return true;
    }

    /**
     * Changes the current time attempting to be reserved
     * @param minsAdded is the time added to the currentTime
     */
    private void setCurrentTime(int minsAdded)
    {
        currentTime = currentTime.plusMinutes(minsAdded);
        if (currentTime.isAfter(endOfDayTime))
        {
            currentTime = startOfDayTime;
            currentDate = currentDate.plusDays(1);
            //Needs to be stopped if it goes past 1 week
        }

    }

    /**
     * Finds a room that can be booked at the current time given the capacity and length
     * @param minCapacity the smallest size a room can be
     * @param sessionLength the length of the lecture/practical
     * @param isSociallyDistanced whether the capacity is restricted due to social distancing
     * @return a room that is available to be booked
     */
    private Room tryBookRoom(int minCapacity, int sessionLength,boolean isSociallyDistanced)
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
