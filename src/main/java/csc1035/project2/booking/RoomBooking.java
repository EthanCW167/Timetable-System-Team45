package csc1035.project2.booking;

import csc1035.project2.HibernateUtil;
import csc1035.project2.booking.reservation.Reservations;
import csc1035.project2.timetable.Module;

import csc1035.project2.util.Controller;
import csc1035.project2.util.IController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that allows the user to book rooms and produce timetables for a room
 *
 * @author Dillon Reed
 * @author Titas Janusonis
 */
public class RoomBooking implements IBooking{
    private List<Room> roomList = new ArrayList<>();
    private Session session;
    private DateTimeFormatter formatter;

    public RoomBooking(List<Room> roomList) {
        this.roomList = roomList;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        session = null;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList.clear();
        this.roomList.addAll(roomList);
    }

    public void addToRoomList(Room room){
        this.roomList.add(room);
    }

    /**
     * This method makes a new room reservation for a given module, and from/to a given time
     * @param r The given Room that is being reserved
     * @param m The given Module that the room is being reserved for
     * @param from The time that the room will be reserved from
     * @param to The time that the room will be reserved until
     * @return Returns 1 if the reservation was made successfully, returns 0 otherwise
     */
    @Override
    public boolean reserveRoom(Room r, Module m, LocalDateTime from, LocalDateTime to) {
        boolean completed = false;
        IController c = new Controller();


        // Adding validation to the parameters
        // Checks if the "from" date is before the current date
        // Checks if the "to" date is before the "from" date
        if(from.isBefore(LocalDateTime.now()) || to.isBefore(from)){
            return false;
        }
        // Formatting the datetime object and inserting a reservation
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query =session.createSQLQuery("INSERT INTO reservations VALUES (:room, :module, :from, :to)");
            query.setParameter("room", r.getRoomNumber());
            query.setParameter("module", m.getId());
            query.setParameter("from", from.format(formatter));
            query.setParameter("to", to.format(formatter));

            query.executeUpdate();
            session.getTransaction().commit();
            completed = true;
        } catch (HibernateException ex) {
            if (session!=null) session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }


        // Returns whether or not the reservation was added
        return completed;
    }

    /**
     * This is a method to delete a reservation
     * @param r The room that the reservation is in
     * @param m The module that the reservation is for
     * @param from The date when the reservation will start
     * @param to The date when the reservation will end
     * @return Returns 1 if the reservation was deleted successfully, returns 0 otherwise
     */
    @Override
    public boolean cancelRoom(Room r, Module m, LocalDateTime from, LocalDateTime to) {
        boolean completed = false;

        // Opening session
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Beginning session
            session.beginTransaction();

            // Finding the correct reservation
            Query query = session.createSQLQuery("DELETE FROM reservations WHERE roomNumber= :room AND moduleId= :module" +
                    " AND `from` = :from AND `to` = :to");
            query.setParameter("room", r.getRoomNumber());
            query.setParameter("module", m.getId());
            query.setParameter("from", from.format(formatter));
            query.setParameter("to", to.format(formatter));

            // Deleting the correct reservation
            query.executeUpdate();

            // End / commit transaction
            session.getTransaction().commit();

            // Setting the completed flag to true
            completed = true;
        }catch (HibernateException e) {
            // If error occurs then rollsback
            if (session!=null){
                session.getTransaction().rollback();
            }
        } finally {
            // Closes the session
            session.close();
        }
        // Returns whether or not the reservation was deleted
        return completed;
    }

    /**
     * This is a method that returns a list of available rooms given certain parameters
     * @param timeStamp When the reservation should start
     * @param forLength How long the reservation should go on for in minutes
     * @param forCapacity How many people the room should hold
     * @return A list of applicable rooms
     */
    @Override
    public ArrayList<Room> findAvailableRooms(LocalDateTime timeStamp, int forLength, int forCapacity) {
        ArrayList<Room> applicableRooms = new ArrayList<>();
        LocalDateTime endTime = timeStamp.plusMinutes(forLength);

        // Loops through each room in the roomList
        for (Room room:roomList) {
            // Checks if the room max capacity is less than the capacity that is needed
            if(room.getMaxCapacity() >= forCapacity){
                // Loops through the reservations for the room

                List<Reservations> reservationsList = createRoomTimetable(room);

                if (reservationsList.size() > 0) {
                    for (Reservations reservation : reservationsList) {
                        // Checks that the "to" of the reservation is before the start time of the "timeStamp" OR
                        // that the reservation "from" time is after the "endTime"
                        if (reservation.getTo().isBefore(timeStamp) || reservation.getFrom().isAfter(endTime)) {
                            if (!applicableRooms.contains(room)) {
                                applicableRooms.add(room);
                            }
                        }
                    }
                }else{
                    applicableRooms.add(room);
                }
            }
        }

        return applicableRooms;
    }

    /**
     * This is a method that returns a list of reservations for a given room
     * @param r The room that the reservations are for
     * @return A list of reservations for the given room
     */
    @Override
    public ArrayList<Reservations> createRoomTimetable(Room r) {
        List roomReservations = new ArrayList();

        // Opening session
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Beginning session
            session.beginTransaction();

            // Makes a list of reservations for a specific room
            Query query = session.createQuery("from reservations where roomNumber=:roomNumber");
            query.setParameter("roomNumber", r.getRoomNumber());

            roomReservations = query.list();

            // End / commit transaction
            session.getTransaction().commit();
        }catch (HibernateException e){
            // If error occurs then rollsback
            if (session!=null){
                session.getTransaction().rollback();
            }
        }finally {
            // Closes session
            session.close();
        }
        // Returns the list of room reservations
        return (ArrayList<Reservations>) roomReservations;
    }

    /**
     * This is a method to update room information
     * @param roomNumber The unique identifier for the room that is to be updated
     * @param newRoom The room information to be put in for the given roomNumber
     * @return Returns 1 if the room was updated successfully, returns 0 otherwise
     */
    @Override
    public boolean updateRoomInfo(String roomNumber, Room newRoom) {
        boolean completed = false;

        // Opening session
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            // Beginning session
            session.beginTransaction();

            // Finding correct Room
            Query query = session.createQuery("from rooms where roomNumber=:roomNumber");
            query.setParameter("roomNumber", roomNumber);
            Room roomToBeUpdated = (Room) query.list().get(0);

            // Adjusting values of the room
            roomToBeUpdated.setRoomNumber(newRoom.getRoomNumber());
            roomToBeUpdated.setType(newRoom.getType());
            roomToBeUpdated.setMaxCapacity(newRoom.getMaxCapacity());
            roomToBeUpdated.setSocialDistancingCapacity(newRoom.getSocialDistancingCapacity());

            // Updating the row in the database
            session.update(roomToBeUpdated);

            // End / commit the transaction
            session.getTransaction().commit();

            // Setting the completed flag to true
            completed = true;
        }catch (HibernateException e) {
            // If error occurs then rollsback
            if (session!=null){
                session.getTransaction().rollback();
            }
        }finally {
            // Closes the session
            session.close();
        }
        // Returns whether or not the room was updated
        return completed;
    }
}
