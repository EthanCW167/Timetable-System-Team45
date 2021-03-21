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
        startDate = LocalDate.now();
        startOfDayTime = LocalTime.of(9,0);
        endOfDayTime = LocalTime.of(15,30);
        currentTime = startOfDayTime;

    }

    public List<Reservations> getReservations(List<String> moduleIds)
    {
        List<Reservations> reservations = new ArrayList<>();
        if (moduleIds != null) {
            for (String moduleID : moduleIds) {
                reservations.addAll((s.createQuery("from reservations where  reservations.moduleId = :id").setParameter("id", moduleID).list()));
                //This gets all the reservations found for each module
            }
        }
        else
        {
            reservations.addAll(s.createQuery("from reservations ").list());

        }
        return reservations;

    }
    public Timetable createTimetable(ArrayList<String> moduleIDs, ArrayList<String> peopleIDs)
    {
        List<String> tempModuleIDs = new ArrayList();
        if (peopleIDs != null) //Find which modules the people take
        {
            for (String personID : peopleIDs) {
                tempModuleIDs = (List<String>) (s.createQuery("from modules join modules.takenByStudent where modules.id =:id")).setParameter("id", personID).list();
            }

           return new Timetable(getReservations(tempModuleIDs));
        }
        else {


            return new Timetable(getReservations(moduleIDs));
        }
    }
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

            Room bookedRoom = null;
            while (bookedRoom == null)
            {
                bookedRoom = tryBookRoom(moduleCapacity,lectureLength,isSociallyDistant);
                setCurrentTime(30); //This is min time between start of one class and end of another

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
