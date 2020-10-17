package Main;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeController {
    private double x,y;
    boolean navigateBool = false;
    @FXML
    private Button searchButton;

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
        showRooms(HotelManagement.rooms);
    }

    @FXML
    public void logout(ActionEvent actionEvent) throws IOException, InterruptedException {
        navigationBarComesIn();
        Navigation.navigateLogin(actionEvent);
    }

    private void showRooms(ArrayList<Room> arrayList) throws IOException {
        vstackList.getChildren().clear();

        RoomPanesController roomPanesController;
        for (Room r: arrayList
        ) {
            FXMLLoader loader = new FXMLLoader();
            Parent parent = loader.load(getClass().getResource("fxml/RoomPanes.fxml").openStream());
            roomPanesController = loader.getController();
            roomPanesController.setIdText(r.getID());
            roomPanesController.setCapacityText(String.valueOf(r.getBedAmount()));
            roomPanesController.setPriceText(String.valueOf(r.getPrice()));
            roomPanesController.setTypeText(r.getType());
            roomPanesController.setImageView(new Image(getClass().getResourceAsStream("/images/" + r.images)));

            vstackList.getChildren().add(parent);
        }
    }

    @FXML
    public DatePicker fromTextField;
    @FXML
    public DatePicker toTextField;


    @FXML
    public void searchAvailableRoom(ActionEvent actionEvent) throws Exception {
        if (fromTextField.getValue() == null || toTextField.getValue() == null){
            HotelManagement.showAlertInformation("Something goes wrong","Fill start and end date");
        }
        else {
            String fromPicker = String.valueOf(fromTextField.getValue());
            String toPicker = String.valueOf(toTextField.getValue());
            ArrayList<Room> arrayList= new ArrayList<>();
            for (Room r : HotelManagement.rooms
            ) {
                if (HotelManagement.checkingReservation(r,fromPicker,toPicker)){
                    arrayList.add(r);
                }
            }
            showRooms(arrayList);
        }
    }
}
