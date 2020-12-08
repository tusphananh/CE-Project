package Main.Models;

// Main.Main.Models.Owner Class
public class Owner {
    private int id;
    private String name, phone,type,identifier;
    private int coins;

    public Owner(int id , String name, String phone,int coins,String type) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.coins = coins;
        this.type = type;
    }

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setCoins(int coins) {
        this.coins = coins;
        if (coins <= 60 && coins >= 0){
            type = "bronze";
        }
        else if (coins > 60 && coins <= 100){
            type = "silver";
        }
        else if (coins > 100 && coins <= 130){
            type = "gold";
        }
        else if (coins > 130){
            type = "diamond";
        }
        else {
            type = "caution";
        }
    }

    public int getCoins() {
        return coins;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // toString here
    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
