package csc1035.project2.timetable;

import csc1035.project2.booking.reservation.Reservations;

import java.util.List;

/**
 * A class that holds the information required for a timetable
 *
 * @author Rowan Lowe
 */
public class Timetable {
    List<Reservations> reservationList;
    public Timetable(List<Reservations> bookingsIn)
    {
        reservationList = bookingsIn;
    }

    @Override
    public String toString() {
        String newLine = System.getProperty("line.separator");
        String stringFormat = "Timetable { " + newLine;
        for (Reservations r:reservationList)
        {
            stringFormat += r.toString() + newLine;

        }

        return stringFormat +
                '}';
    }


}
