package csc1035.project2.booking;

import javax.persistence.*;

/**
 * A class for abstract Room objects
 *
 * @author Dillon Reed
 */
@Entity(name = "rooms")
public class Room {
    @Id
    @Column(name = "roomNumber")
    private String roomNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "maxCapacity")
    private int maxCapacity;

    @Column(name = "socialDistancingCapacity")
    private int socialDistancingCapacity;

    public Room(String roomNumber, String type, int maxCapacity, int socialDistancingCapacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.socialDistancingCapacity = socialDistancingCapacity;
    }

    public Room() {}

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getSocialDistancingCapacity() {
        return socialDistancingCapacity;
    }

    public void setSocialDistancingCapacity(int socialDistancingCapacity) {
        this.socialDistancingCapacity = socialDistancingCapacity;
    }
}
