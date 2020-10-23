package Main.Models;

import Main.Enums.PaymentStatus;

public class ReservationOnline extends Reservation {
    PaymentStatus paymentStatus;
    PaymentMethod paymentMethod;

    public ReservationOnline(String id, String from, String to, PaymentMethod paymentMethod, Owner owner, Room room) throws Exception {
        super(id, from, to, owner, room);
        this.paymentStatus = PaymentStatus.pending;
        this.paymentMethod = paymentMethod;
    }

    // toString here

    @Override
    public String toString() {
        return "ReservationOnline{" +
                "duration=" + duration +
                ", reservedStatus=" + reservedStatus +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + room +
                ", totalPrice=" + totalPrice +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod.getClass() +
                '}';
    }
}
