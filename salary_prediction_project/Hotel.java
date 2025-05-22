package HotelReservation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public List<Room> searchRooms(RoomCategory category) {
        return rooms.stream().filter(room -> room.getCategory() == category && room.isAvailable()).collect(Collectors.toList());
    }

    public Reservation makeReservation(User user, Room room, Date checkIn, Date checkOut) {
        if (room.isAvailable()) {
            Reservation reservation = new Reservation(user, room, checkIn, checkOut);
            if (Payment.processPayment(user, reservation.getTotalPrice())) {
                room.setAvailable(false);
                reservations.add(reservation);
                return reservation;
            }
        }
        return null;
    }

    public List<Reservation> viewReservations(User user) {
        return reservations.stream().filter(reservation -> reservation.getUser().equals(user)).collect(Collectors.toList());
    }
}

