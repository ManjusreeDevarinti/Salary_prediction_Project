package HotelReservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Hotel hotel = new Hotel();

        // Adding some rooms to the hotel
        hotel.addRoom(new Room(101, RoomCategory.SINGLE, true, 100.0));
        hotel.addRoom(new Room(102, RoomCategory.DOUBLE, true, 150.0));
        hotel.addRoom(new Room(201, RoomCategory.SUITE, true, 300.0));

        Scanner scanner = new Scanner(System.in);

        // User input for user details
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();

        User user = new User(name, email);

        // User input for room category
        System.out.println("Enter room category (SINGLE, DOUBLE, SUITE): ");
        String categoryInput = scanner.nextLine();
        RoomCategory category = RoomCategory.valueOf(categoryInput.toUpperCase());

        // Searching for rooms
        List<Room> availableRooms = hotel.searchRooms(category);
        System.out.println("Available " + category + " rooms: " + availableRooms.size());

        if (!availableRooms.isEmpty()) {
            // Displaying available rooms
            for (Room room : availableRooms) {
                System.out.println("Room number: " + room.getRoomNumber() + ", Price: $" + room.getPrice());
            }

            // User input for room selection
            System.out.println("Enter room number to book: ");
            int roomNumber = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            // User input for check-in and check-out dates
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter check-in date (dd/MM/yyyy): ");
            String checkInInput = scanner.nextLine();
            System.out.println("Enter check-out date (dd/MM/yyyy): ");
            String checkOutInput = scanner.nextLine();

            Date checkIn = sdf.parse(checkInInput);
            Date checkOut = sdf.parse(checkOutInput);

            // Making the reservation
            Room roomToBook = null;
            for (Room room : availableRooms) {
                if (room.getRoomNumber() == roomNumber) {
                    roomToBook = room;
                    break;
                }
            }

            if (roomToBook != null) {
                Reservation reservation = hotel.makeReservation(user, roomToBook, checkIn, checkOut);
                if (reservation != null) {
                    System.out.println("Reservation made for " + reservation.getUser().getName() + " in room " + reservation.getRoom().getRoomNumber());
                    System.out.println("Total price: $" + reservation.getTotalPrice());
                } else {
                    System.out.println("Reservation failed");
                }
            } else {
                System.out.println("Room not found");
            }

            // Viewing reservations for the user
            List<Reservation> reservations = hotel.viewReservations(user);
            System.out.println("Reservations for " + user.getName() + ": " + reservations.size());
        } else {
            System.out.println("No rooms available for the selected category.");
        }

        scanner.close();
    }
}
