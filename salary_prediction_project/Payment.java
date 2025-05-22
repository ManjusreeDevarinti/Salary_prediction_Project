package HotelReservation;

public class Payment {
    public static boolean processPayment(User user, double amount) {
        // Simplified payment processing
        System.out.println("Processing payment for " + user.getName() + " of amount $" + amount);
        return true;
    }
}