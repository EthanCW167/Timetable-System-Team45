package csc1035.project2.booking.reservation;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * An ID class for Reservation table in DB
 *
 * @author Titas Janusonis
 */
public class ReservationID implements Serializable {
    private String roomNumber;
    private String moduleId;
    private LocalDateTime from;
    private  LocalDateTime to;

    public ReservationID(){}

    public ReservationID(String roomNumber, String moduleId,
                         LocalDateTime from, LocalDateTime to) {
        this.roomNumber = roomNumber;
        this.moduleId = moduleId;
        this.from = from;
        this.to = to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, moduleId,
                from, to);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationID ResID = (ReservationID) o;
        return Objects.equals(roomNumber, ResID.roomNumber) &&
                Objects.equals(moduleId, ResID.moduleId) &&
                Objects.equals(from, ResID.from) &&
                Objects.equals(to, ResID.to);
    }
}
