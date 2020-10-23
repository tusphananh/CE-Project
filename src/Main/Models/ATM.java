package Main.Models;

import Main.Main;

public class ATM extends PaymentMethod {
    String owner;

    ATM(String id, String owner) {
        super(id);
        this.owner = owner;
        this.name = "ATM";
    }
}
