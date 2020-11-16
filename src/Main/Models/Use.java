package Main.Models;

import java.util.ArrayList;

public class Use {
    private Service service;
    private long amount;
    private double totalPrice;

    public Use(Service service,long amount){
        this.service = service;
        this.amount = amount;
        setTotalPrice();
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public long getAmount() {
        return amount;
    }

    public Service getService() {
        return service;
    }

    public void setTotalPrice() {
        this.totalPrice = this.amount*service.getPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
