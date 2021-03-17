package csc1035.project2.timetable;

import csc1035.project2.booking.reservation.Reservations;

import java.util.List;

public class Timetable {
    List<Reservations> reservationList;
    public Timetable(List<Reservations> bookingsIn)
    {
        reservationList = bookingsIn;


    }

    @Override
    public String toString() {
        String stringFormat = "Timetable /n {";
        for (Reservations r:reservationList)
        {
            stringFormat += r.toString() + "/n";

        }

        return "Timetable{" +

                stringFormat +
                '}';
    }


}
