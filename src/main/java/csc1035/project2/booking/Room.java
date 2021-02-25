package csc1035.project2.booking;

/**
 * A class for abstract Room objects
 *
 * @author Dillon Reed
 */
public class Room {
    private double roomNumber;
    private String type;
    private int maxCapacity;
    private int socialDistancingCapacity;

    public Room(double roomNumber, String type, int maxCapacity, int socialDistancingCapacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.maxCapacity = maxCapacity;
        this.socialDistancingCapacity = socialDistancingCapacity;
    }

    public Room() {}

    public double getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(double roomNumber) {
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
