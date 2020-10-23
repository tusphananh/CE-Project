package Restaurant;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Food implements Initializable {
    int foodID, price, qty;
    String name;

    static ArrayList<Food> foodList = new ArrayList<Food>();

    public static ArrayList<Food> getFoodList(){
        return foodList;
    }

    public Food(int i, int p, String n){  // Constructor
        foodID = i;
        price = p;
        name = n;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
