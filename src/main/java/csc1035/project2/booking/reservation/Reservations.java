package csc1035.project2.booking.reservation;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * A class for Reservation table in DB
 *
 * @author Titas Janusonis
 */
@Entity(name = "reservations")
@IdClass(ReservationID.class)
public class Reservations {
    @Id
    @Column(name = "roomNumber")
    private String roomNumber;

    @Id
    @Column(name = "moduleId")
    private String moduleId;

    @Id
    @Column(name = "from")
    private LocalDateTime from;

    @Id
    @Column(name = "to")
    private LocalDateTime to;

    public Reservations() {
    }

    public Reservations(String roomNumber, String moduleId, LocalDateTime from, LocalDateTime to) {
        this.roomNumber = roomNumber;
        this.moduleId = moduleId;
        this.from = from;
        this.to = to;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
}
