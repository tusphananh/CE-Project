package Main.Models;


import java.util.ArrayList;

public class ReservationOnline extends Reservation {
    String paymentStatus;
    PaymentMethod paymentMethod;

    public ReservationOnline(String id, String from, String to, PaymentMethod paymentMethod, Owner owner, ArrayList<Room> rooms) throws Exception {
        super(id, from, to, owner, rooms);
        this.paymentStatus = "pending";
        this.paymentMethod = paymentMethod;
    }

    // toString here

    @Override
    public String toString() {
        return "ReservationOnline{" +
                "duration=" + duration +
                ", status=" + status +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + rooms +
                ", totalPrice=" + totalPrice +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod.getClass() +
                '}';
    }
}
