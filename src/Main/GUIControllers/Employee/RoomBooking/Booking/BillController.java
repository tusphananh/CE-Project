package Main.GUIControllers.Employee.RoomBooking.Booking;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.ParseException;

public class BillController {
    @FXML
    private ScrollPane listOfPane;

    @FXML
    private VBox vstack,serviceStack;

    @FXML
    private FlowPane serviceFlowPane;

    @FXML
    private Text totalText;

    @FXML
    private TextField nameTextField,phoneTextField,noteTextField;

    @FXML
    void initialize() throws IOException, ParseException {
        loadSelectedRoom();
        loadTotalPrice();
        loadServices();
        loadServicesStack();
    }

    public void loadTotalPrice() throws ParseException {
        totalText.setText(RoomBookingManagement.getTotalPrice() + " $");
    }

    public void loadSelectedRoom() throws IOException {
        vstack.getChildren().clear();
        for (Room r: RoomBookingManagement.selectedRoom
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/Bill_Item.fxml"));
            Parent bill_itemPane = fxmlLoader.load();
            Bill_ItemController bill_itemController = fxmlLoader.getController();
            bill_itemController.setRoom(r);
            bill_itemController.setRoomLabel(r.getID());
            bill_itemController.setPriceLabel(String.valueOf(r.getSalePrice()));
            bill_itemController.setTypeLabel(r.getType());
            vstack.getChildren().add(bill_itemPane);
        }
    }

    @FXML
    void confirm(ActionEvent event) throws Exception {
        RoomBookingManagement.setNote(noteTextField.getText());
        if (!nameTextField.getText().equals("") && !phoneTextField.getText().equals("")){
            RoomBookingManagement.setOwner(new Owner(nameTextField.getText(),phoneTextField.getText()));
            RoomBookingManagement.addOwner();
            RoomBookingManagement.addReservation();
            Navigation.getDayPickController().showRoomBookingSuccess();
        }
    }

    public void loadServicesStack() throws IOException {
        serviceStack.getChildren().clear();
        for (Use use: RoomBookingManagement.selectedUse
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/ServicePane.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServicePaneController servicePaneController = fxmlLoader.getController();
            servicePaneController.setPriceLabel(RoomBookingManagement.moneyFormat(String.valueOf(use.getTotalPrice())));
            servicePaneController.setNameLabel(use.getService().getName());
            servicePaneController.setAmountLabel(String.valueOf(use.getAmount()));
            serviceStack.getChildren().add(servicePane);
        }
    }

    void loadServices() throws IOException {
        for (Service service: RoomBookingManagement.services
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/ServiceButton.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServiceButtonController serviceButtonController = fxmlLoader.getController();
            serviceButtonController.setService(service);
            serviceButtonController.setNameLabel(service.getName());
            serviceFlowPane.getChildren().add(servicePane);
        }
    }

    @FXML
    void back(MouseEvent event) {
       close();
    }
    public void close(){
        Navigation.getRoomPickController().slideHideBill();
    }

}
