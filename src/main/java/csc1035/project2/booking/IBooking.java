package csc1035.project2.booking;

import csc1035.project2.booking.reservation.Reservations;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Booking interface
 *
 * @author Titas Janusonis
 */
public interface IBooking {

    // Makes a reservation returns 1 if successful 0 if not
    boolean reserveRoom(Room r, Module m, LocalDateTime from, LocalDateTime to);
    // Deletes a reservation returns 1 if successful 0 if not
    boolean cancelRoom(Room r, Module m, LocalDateTime from, LocalDateTime to);
    // Finds all available rooms with capacity starting with a timeStamp ending after length in minutes
    ArrayList<Room> findAvailableRooms(LocalDateTime timeStamp, int forLength, int forCapacity);
    // Creates a list of reservations for given room
    ArrayList<Reservations> createRoomTimetable(Room r); // One week is good
    // Update room info
    boolean updateRoomInfo(Room oldRoom, Room newRoom);
}
