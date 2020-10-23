package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class EmployeeController {
    private double x,y;
    private double reloadSpeed = 300;
    boolean navigateBool = false;
    private Pane checkInPane,checkOutPane,restaurantPane;

    @FXML
    private Button searchButton;

    @FXML
    private AnchorPane mainUI;

    @FXML
    private Pane bookingPane;

    @FXML
    private VBox vstackList;

    @FXML
    private ScrollPane listOfPane;

    @FXML
    private Pane navigationBar;
    @FXML
    public void initialize() throws IOException, InterruptedException {
        showRooms(HotelManagement.rooms);
        mainUI.setOpacity(0);
        Navigation.fadeOut(mainUI,1000);
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
    void toBooking(MouseEvent event) throws IOException {
        navigationBarComesIn();
        bookingPane.setVisible(true);

    }

    @FXML
    void toCheckOut(MouseEvent event) throws IOException {
        navigationBarComesIn();
        bookingPane.setVisible(false);
    }
    @FXML
    void toCheckIn(MouseEvent event) throws IOException {
        navigationBarComesIn();
        bookingPane.setVisible(false);

    }
    @FXML
    void toRestaurant(MouseEvent event) throws IOException {
        navigationBarComesIn();
        bookingPane.setVisible(false);
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
    public void logout(ActionEvent actionEvent) throws IOException, InterruptedException {
        navigationBarComesIn();
        Navigation.navigateLogin(actionEvent);
    }

    private void showRooms(Collection<Room> arrayList) throws IOException {
        vstackList.setOpacity(0);
        vstackList.getChildren().clear();

        RoomPanesController roomPanesController;
        for (Room r: arrayList
        ) {
            FXMLLoader loader = new FXMLLoader();
            Parent parent = loader.load(getClass().getResource("../fxml/RoomPanes.fxml").openStream());
            roomPanesController = loader.getController();
            roomPanesController.setIdText(r.getID());
            roomPanesController.setCapacityText(String.valueOf(r.getCapacity()));
            roomPanesController.setPriceText(String.valueOf(r.getPrice()));
            roomPanesController.setTypeText(r.getType());
            roomPanesController.setImageView(new Image(getClass().getResourceAsStream("/images/" + r.images)));
            if (r.sale > 0.00){
                roomPanesController.showSale();
                roomPanesController.setSaleText(String.valueOf(r.sale*100));
            }
            else {
                roomPanesController.hideSale();
            }
            vstackList.getChildren().add(parent);
            FadeTransition fadeTransition = new FadeTransition();
            fadeTransition.setDuration(Duration.millis(reloadSpeed));
            fadeTransition.setNode(vstackList);
            fadeTransition.setFromValue(0);
            fadeTransition.setToValue(1);
            fadeTransition.play();
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

    @FXML
    public void sortByCapacity() throws IOException {
        Collections.sort( HotelManagement.rooms, new Comparator<Room>() {
            public int compare (Room o1, Room o2) {
                if (o1.capacity > o2.capacity){
                    return -1;
                }
                else if (o1.capacity < o2.capacity){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(reloadSpeed));
        fadeTransition.setNode(vstackList);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
        showRooms(HotelManagement.rooms);

    }

    @FXML
    public void sortByHot() throws IOException {
        Collections.sort( HotelManagement.rooms, new Comparator<Room>() {
            public int compare (Room o1, Room o2) {
                int comp = (o2.hot - o1.hot);
                return comp;
            }
        });
        showRooms(HotelManagement.rooms);
    }

    @FXML
    public void sortBySale() throws IOException {
        Collections.sort( HotelManagement.rooms, new Comparator<Room>() {
            public int compare (Room o1, Room o2) {
                if (o1.sale > o2.sale){
                    return -1;
                }
                else if (o1.sale < o2.sale){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        showRooms(HotelManagement.rooms);
    }


}
