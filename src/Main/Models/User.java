package Main.Models;

public class User {
    private int id;
    private String name, phone,role,identifier,password,username,status,image,dob;
    public User(int id, String name , String phone , String identifier,String username,String dob,String password,String role,String image,String status){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.identifier = identifier;
        this.password = password;
        this.status = status;
        this.username = username;
        this.dob = dob;
        this.image = image;
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

    public String getPhone() {
        return phone;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public String getDob() {
        return dob;
    }
}
