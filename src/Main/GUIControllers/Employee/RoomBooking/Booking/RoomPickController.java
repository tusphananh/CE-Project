package Main.GUIControllers.Employee.RoomBooking.Booking;

import Main.Models.RoomBookingManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoomPickController {
    private double reloadSpeed = 500;
    private VBox billPane;
    private BillController billController;
    private ArrayList<Room> availableRoom;

    @FXML
    private VBox bookingPane;

    @FXML
    private javafx.scene.layout.FlowPane FlowPane;

    @FXML
    private ScrollPane listOfPane;

    @FXML
    public Button basketButton;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        availableRoom = RoomBookingManagement.availableRooms;
        showRooms(availableRoom);
        updateBasketButton();
    }

    public void showRooms(ArrayList<Room> arrayList) throws IOException {
        FlowPane.setOpacity(0);
        FlowPane.getChildren().clear();
        RoomPanesController roomPanesController;
        for (Room r: arrayList
        ) {
            System.out.println(r.toString());
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/RoomPanes.fxml"));
            Parent parent = loader.load();
            roomPanesController = loader.getController();
            roomPanesController.setIdText(r.getName());
            roomPanesController.setCapacityText(String.valueOf(r.getCapacity()));
            roomPanesController.setTypeText(r.getType());
            try {
                roomPanesController.setImageView(new Image(getClass().getResourceAsStream("/images/" + r.images)));
            }
            catch (Exception e){
                System.out.println("Room ID " + r.getID() + " has invalid image");
            }
            roomPanesController.setRoom(r);
            if (r.sale > 0){
                roomPanesController.showSale();
                roomPanesController.setSaleText(String.valueOf(r.sale));
                roomPanesController.setPriceText(r.getPrice() + " to " + r.getSalePrice());
            }
            else {
                roomPanesController.hideSale();
                roomPanesController.setPriceText(String.valueOf(r.getPrice()));
            }
            FlowPane.getChildren().add(parent);
            Navigation.fadeOut(FlowPane,reloadSpeed);
        }
    }

    @FXML
    public void back(ActionEvent actionEvent){
        Navigation.getDayPickController().hideSlideRoomPicker();
    }

    @FXML
    public void sortByCapacity() throws IOException {
        Collections.sort( RoomBookingManagement.availableRooms, new Comparator<Room>() {
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
    public void sortBySale() throws IOException {
        Collections.sort( RoomBookingManagement.availableRooms, new Comparator<Room>() {
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

    public void updateBasketButton(){
        if (!RoomBookingManagement.selectedRoom.isEmpty()){
            basketButton.setDisable(false);
            basketButton.setText(RoomBookingManagement.selectedRoom.size() + " ITEM");
            Navigation.fadeOut(basketButton,300);
        }
        else {
            basketButton.setDisable(true);
            basketButton.setText("Basket");
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
                    showRooms(availableRoom);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        fadeTransition.play();
    }

    @FXML
    void showBill(ActionEvent actionEvent) throws IOException {
        loadBill();
        slideShowBill();
    }

    void slideShowBill(){
        billPane.setVisible(true);
        Navigation.slideVerticallyTransition(billPane,0,300);
    }

    void slideHideBill(){
        TranslateTransition swipeTransition = new TranslateTransition();
        swipeTransition.setNode(billPane);
        swipeTransition.setDuration(Duration.millis(300));
        swipeTransition.setToY(bookingPane.getHeight());
        swipeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                billPane.setVisible(false);
            }
        });
        swipeTransition.play();
    }

    void loadBill() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/Bill.fxml"));
        billPane = fxmlLoader.load();
        billPane.setTranslateY(bookingPane.getHeight());
        billPane.setVisible(false);
        Navigation.setBillController(fxmlLoader.getController());
        Navigation.getDayPickController().getMainStack().getChildren().add(billPane);
    }
}
