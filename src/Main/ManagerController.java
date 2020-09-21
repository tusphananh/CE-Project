package Main;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.w3c.dom.Text;

import javax.print.DocFlavor;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerController {
    private double x,y;
    boolean navigateBool = false;
    @FXML
    private VBox vstackList;

    @FXML
    private javafx.scene.text.Text roomText;

    @FXML
    private javafx.scene.text.Text capacityText;

    @FXML
    private javafx.scene.text.Text availableText;

    @FXML
    private javafx.scene.control.ScrollPane listOfPanels;

    @FXML
    private Pane navigationBar;

    public ManagerController() throws IOException {
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
    public void minimize(ActionEvent actionEvent){
        Navigation.minimize(actionEvent);
    }

    @FXML
    public void exit(ActionEvent actionEvent){
        System.out.println("Exit");
        System.exit(-1);
    }

    @FXML
    public void extendedNavigationBar(ActionEvent actionEvent) {
        Node node = navigationBar;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), node);
        navigateBool = !navigateBool;
        if (navigateBool){
            translateTransition.setToX(0);
            translateTransition.play();
            navigationBar.setTranslateX(0);
        }
        else {
            translateTransition.setToX(-210);
            translateTransition.play();
            navigationBar.setTranslateX(-210);
        }

    }

    @FXML
    public void initialize() throws IOException {

        for (Room r: ViewController.getRooms()
             ) {
            Node newLoadedPane = FXMLLoader.load(getClass().getResource("item.fxml"));
            vstackList.getChildren().add(newLoadedPane);
        }
    }
}
