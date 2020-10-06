package Main;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class EmployeeController {
    private double x,y;
    boolean navigateBool = false;
    @FXML
    private Pane userManagementPane,managementPane;

    @FXML
    private VBox vstackList;

    @FXML
    private ScrollPane listOfPane;

    @FXML
    private Pane navigationBar;

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
    public void extendedNavigationBar(MouseEvent actionEvent) {
        Node node = navigationBar;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), node);
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
    void toUserManagement(MouseEvent event) throws IOException {
        navigationBarComesIn();
        managementPane.setVisible(false);
        userManagementPane.setVisible(true);
    }

    @FXML
    void toManagement(MouseEvent event) throws IOException {
        navigationBarComesIn();
        userManagementPane.setVisible(false);
        managementPane.setVisible(true);
    }


    void navigationBarComesIn(){
        navigateBool = !navigateBool;
        Node node = navigationBar;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300), node);
        translateTransition.setToX(-210);
        translateTransition.play();
        navigationBar.setTranslateX(-210);
    }

    @FXML
    public void initialize() throws IOException {
        RoomPanels roomPanels;
        for (Room r: HotelManagement.rooms
        ) {
            roomPanels = new RoomPanels(r.getID(),r.getBedAmount(),r.getPrice(),r.getType(),r.images);
            roomPanels.initilize();
            vstackList.getChildren().add(roomPanels);
        }

    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException, InterruptedException {
        Navigation.navigateLogin(actionEvent);
    }

}
