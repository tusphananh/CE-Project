package Restaurant;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderScreenController implements Initializable {

    public VBox BoxMenu;
    ArrayList<Food> foods = Food.getFoodList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setMenuPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMenuPane() throws IOException {
        BoxMenu.getChildren().clear();

        FoodItemController foodController;
        for (Food f : foods) {
            System.out.println("Food");
            FXMLLoader loader = new FXMLLoader();
            Parent parent = loader.load(getClass().getResource("FoodItem.fxml").openStream());
            foodController = loader.getController();
            foodController.setNameLabel(f.name);
            foodController.setPriceLabel(f.price);

            BoxMenu.getChildren().add(parent);

        }
    }
}
