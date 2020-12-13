package Main.GUIControllers.Employee.Service;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ServicePickController {
    private ArrayList<RoomBooking> reservations = new ArrayList<>();
    private RoomBooking selectedRoomBooking;
    private ArrayList<Use> selectedUse = new ArrayList<>();

    @FXML
    private VBox bookingPane;

    @FXML
    private Label chosenText;

    @FXML
    private ScrollPane listOfPane;

    @FXML
    private javafx.scene.layout.FlowPane serviceStack;

    @FXML
    private VBox selectedStack,reservationStack;

    @FXML
    private Button basketButton;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button searchButton;

    @FXML
    void initialize() throws Exception {
        basketButton.setOpacity(0);
        loadService();
        loadReservationStack();
    }

    @FXML
    void confirm(ActionEvent actionEvent) throws Exception {
        if (selectedRoomBooking != null){
            ServiceManagement.updateUses(selectedRoomBooking,selectedUse);
            Navigation.getDayPickController().showServiceSuccess();
        }
        else {
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(primaryStage);
            alert.setTitle("Missing");
            alert.setHeaderText("Missing reservation");
            alert.setContentText("Please choose your reservation");
            alert.showAndWait();
        }
    }

    @FXML
    void search(ActionEvent actionEvent) throws IOException {
        loadReservationStack(findRoomBooking(phoneTextField.getText()));
    }

    @FXML
    void searchByEnter(KeyEvent keyEvent){
        if(keyEvent.getCode() == KeyCode.ENTER){
            searchButton.fire();
        }
    }

    void loadReservation() throws Exception {
        reservations.clear();
        reservations.addAll(ServiceManagement.getUsingReservation());
    }

    private RoomBooking findRoomBooking(String phone){
        for (RoomBooking roomBooking: reservations
        ) {
            if(roomBooking.getOwner().getPhone().equals(phone)){
                return roomBooking;
            }
        }
        return null;
    }

    void loadReservationStack() throws Exception {
        loadReservation();
        reservationStack.getChildren().clear();
        for (RoomBooking r: reservations
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/Service/CheckPane.fxml"));
            Parent servicePane = fxmlLoader.load();
            CheckPaneController checkPaneController = fxmlLoader.getController();
            checkPaneController.setReservation(r);
            checkPaneController.setFirstID(r.getFirst());
            checkPaneController.setLastID(r.getOwner().getPhone());
            checkPaneController.setName(r.getOwner().getName());

            reservationStack.getChildren().add(servicePane);
        }
    }

    void loadReservationStack(RoomBooking r) throws IOException {
        reservationStack.getChildren().clear();
        Parent servicePane;
       if (r != null){
           FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/Service/CheckPane.fxml"));
           servicePane = fxmlLoader.load();
           CheckPaneController checkPaneController = fxmlLoader.getController();
           checkPaneController.setReservation(r);
           checkPaneController.setFirstID(r.getFirst());
           checkPaneController.setLastID(r.getOwner().getPhone());
           checkPaneController.setName(r.getOwner().getName());
       }
       else {
           servicePane = new Label("No result");
       }
        reservationStack.getChildren().add(servicePane);
    }

    void loadService() throws IOException, SQLException {
        for (Service service: ServiceManagement.getServices()
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/Service/ServiceButton.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServiceButtonController serviceButtonController = fxmlLoader.getController();
            serviceButtonController.setService(service);
            serviceButtonController.setNameLabel(service.getName());
            serviceStack.getChildren().add(servicePane);
        }
    }
    void loadSelectedUses() throws IOException {
        selectedStack.getChildren().clear();
        for (Use use: selectedUse
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/Service/ServicePane.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServicePaneController servicePaneController = fxmlLoader.getController();
            servicePaneController.setPriceLabel(RoomBookingManagement.moneyFormat(String.valueOf(use.getTotalPrice())));
            servicePaneController.setNameLabel(use.getService().getName());
            servicePaneController.setAmountLabel(String.valueOf(use.getAmount()));
            selectedStack.getChildren().add(servicePane);
        }
    }

    public void updateBasketButton(){
        if (!selectedUse.isEmpty()){
            basketButton.setDisable(false);
            basketButton.setText(selectedUse.size() + " Item");
            Navigation.fadeOut(basketButton,300);
        }
        else {
            basketButton.setDisable(true);
            basketButton.setText("Basket");
            Navigation.fadeIn(basketButton,300);
        }
    }

    public void loadChosenText(){
        chosenText.setText(selectedRoomBooking.getFirst() + " - " + selectedRoomBooking.getId() + " - " + selectedRoomBooking.getOwner().getName());
    }

    public void setSelectedRoomBooking(RoomBooking selectedRoomBooking) {
        this.selectedRoomBooking = selectedRoomBooking;
    }

    public  ArrayList<Use> getSelectedUse() {
        return selectedUse;
    }
}
