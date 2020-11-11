package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class EmployeeController{
    private double reloadSpeed = 500;
    static boolean navigateBool = false;
    public static String from,to;

    private VBox checkInPane,checkOutPane,restaurantPane;
    private VBox slider;

    @FXML
    private StackPane stackPane,mainStack;
    @FXML
    public DatePicker fromTextField;
    @FXML
    public DatePicker toTextField;

    @FXML
    private Button searchButton;

    @FXML
    private VBox mainPane,bookingPane;

    @FXML
    private FlowPane FlowPane;

    @FXML
    private ScrollPane listOfPane;

    @FXML
    public Button basketButton;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        checkOutPane = FXMLLoader.load(getClass().getResource("../fxml/CheckOut.fxml"));
        checkInPane = FXMLLoader.load(getClass().getResource("../fxml/CheckIn.fxml"));
        restaurantPane = FXMLLoader.load(getClass().getResource("../fxml/Restaurant.fxml"));
        slider = FXMLLoader.load(getClass().getResource("../fxml/Slider.fxml"));
        stackPane.getChildren().add(slider);
        slider.setTranslateX(-(slider.getPrefWidth() + 30 ));
        showBooking();
        showRooms(HotelManagement.rooms);
        stackPane.setOpacity(0);
        Navigation.fadeOut(stackPane,1000);
        setBasketButtonContent("",false);
    }

    private void showRooms(Collection<Room> arrayList) throws IOException {
        FlowPane.setOpacity(0);
        FlowPane.getChildren().clear();
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
            roomPanesController.setRoom(r);
            if (r.sale > 0.00){
                roomPanesController.showSale();
                roomPanesController.setSaleText(String.valueOf(r.sale*100));
            }
            else {
                roomPanesController.hideSale();
            }
            FlowPane.getChildren().add(parent);
            Navigation.fadeOut(FlowPane,reloadSpeed);
        }
    }


    @FXML
    public void searchAvailableRoom(ActionEvent actionEvent) throws Exception {
        if (fromTextField.getValue() == null || toTextField.getValue() == null){
            HotelManagement.showAlertInformation("Something goes wrong","Fill start and end date");
        }
        else {
             from = String.valueOf(fromTextField.getValue());
             to = String.valueOf(toTextField.getValue());
            ArrayList<Room> arrayList= new ArrayList<>();
            for (Room r : HotelManagement.rooms
            ) {
                if (HotelManagement.checkingRoom(r,from,to)){
                    arrayList.add(r);
                }
            }
            refreshRoomPanes();
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
        refreshRoomPanes();
    }

    @FXML
    public void sortByHot() throws IOException {
        Collections.sort( HotelManagement.rooms, new Comparator<Room>() {
            public int compare (Room o1, Room o2) {
                int comp = (o2.hot - o1.hot);
                return comp;
            }
        });
        refreshRoomPanes();
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
        refreshRoomPanes();
    }

    @FXML
    void onMouseClicked(MouseEvent mouseEvent){
        if (navigateBool){
            slideTransition();
        }
    }

    @FXML
    void slide(ActionEvent actionEvent){
        slideTransition();
    }

    void slideTransition(){
        navigateBool = !navigateBool;
        if (navigateBool){
            Navigation.slideTransition(slider,0,300);
        }
        else{
            Navigation.slideTransition(slider,-(slider.getPrefWidth() + 30 ),300);
        }
    }

    public void setBasketButtonContent(String content,Boolean isVisible){
        basketButton.setText(content);
        if (isVisible){
            Navigation.fadeOut(basketButton,300);
        }
        else {
            Navigation.fadeIn(basketButton,300);
        }
    }

    private void refreshRoomPanes(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(reloadSpeed));
        fadeTransition.setNode(FlowPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    showRooms(HotelManagement.rooms);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fadeTransition.play();
    }

    void showBooking(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(bookingPane);
    }
    void showCheckIn(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(checkInPane);
    }
    void showCheckOut(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(checkOutPane);
    }
    void showRestaurant(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(restaurantPane);
    }

    public String getFromTextField() {
        return String.valueOf(fromTextField.getValue());
    }

    public String getToTextField() {
        return String.valueOf(toTextField.getValue());
    }

}
