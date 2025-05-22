package HotelReservation;

import java.util.Date;

public class Reservation {
    private User user;
    private Room room;
    private Date checkIn;
    private Date checkOut;
    private double totalPrice;

    public Reservation(User user, Room room, Date checkIn, Date checkOut) {
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = room.getPrice() * (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}