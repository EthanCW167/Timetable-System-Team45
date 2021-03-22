package csc1035.project2.booking;

import csc1035.project2.booking.reservation.Reservations;
import csc1035.project2.timetable.Module;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Booking interface
 *
 * @author Titas Janusonis
 */
public interface IBooking {

    // Makes a reservation returns 1 if successful 0 if not

    /**
     * This method makes a new room reservation for a given module, and from/to a given time
     * @param r The given Room that is being reserved
     * @param m The given Module that the room is being reserved for
     * @param from The time that the room will be reserved from
     * @param to The time that the room will be reserved until
     * @return Returns 1 if the reservation was made successfully, returns 0 otherwise
     */
    boolean reserveRoom(Room r, Module m, LocalDateTime from, LocalDateTime to);
    // Deletes a reservation returns 1 if successful 0 if not
    /**
     * This is a method to delete a reservation
     * @param r The room that the reservation is in
     * @param m The module that the reservation is for
     * @param from The date when the reservation will start
     * @param to The date when the reservation will end
     * @return Returns 1 if the reservation was deleted successfully, returns 0 otherwise
     */
    boolean cancelRoom(Room r, Module m, LocalDateTime from, LocalDateTime to);
    // Finds all available rooms with capacity starting with a timeStamp ending after length in minutes
    /**
     * This is a method that returns a list of available rooms given certain parameters
     * @param timeStamp When the reservation should start
     * @param forLength How long the reservation should go on for in minutes
     * @param forCapacity How many people the room should hold
     * @return A list of applicable rooms
     */
    ArrayList<Room> findAvailableRooms(LocalDateTime timeStamp, int forLength, int forCapacity);
    // Creates a list of reservations for given room
    /**
     * This is a method that returns a list of reservations for a given room
     * @param r The room that the reservations are for
     * @return A list of reservations for the given room
     */
    ArrayList<Reservations> createRoomTimetable(Room r); // One week is good
    // Update room info
    /**
     * This is a method to update room information
     * @param roomNumber The unique identifier for the room that is to be updated
     * @param newRoom The room information to be put in for the given roomNumber
     * @return Returns 1 if the room was updated successfully, returns 0 otherwise
     */
    boolean updateRoomInfo(String roomNumber, Room newRoom);
}
