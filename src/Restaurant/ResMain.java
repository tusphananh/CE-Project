package Restaurant;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ResMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("OrderScreen.fxml"));
        primaryStage.setTitle("123");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Food f1 = new Food(1,12,"F1");
        Food f2 = new Food(2,23,"F2");
        Food.foodList.add(f1);
        Food.foodList.add(f2);

        launch(args);
    }
}