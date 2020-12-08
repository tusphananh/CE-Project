package Main.Models;

public class User {
    private int id;
    private String name, phone,role,identifier,password,username,status;
    public User(int id, String name , String phone , String identifier,String username,String password,String role,String status){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.identifier = identifier;
        this.password = password;
        this.status = status;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}
