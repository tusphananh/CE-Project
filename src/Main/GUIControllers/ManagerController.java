package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerController {
    private double x,y;
    @FXML
    void exit(ActionEvent event) {
        System.out.println("Exit");
        System.exit(-1);
    }

    @FXML
    void minimize(ActionEvent event) {
        Navigation.minimize(event);
    }

    @FXML
    public void mousePressed(MouseEvent mouseEvent){
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    @FXML
    public void mouseDraged(MouseEvent mouseEvent){
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x );
        stage.setY(mouseEvent.getScreenY() - y );
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException, InterruptedException {
        System.out.println("LogOut");
        Navigation.navigateLogin(actionEvent);
    }

}
