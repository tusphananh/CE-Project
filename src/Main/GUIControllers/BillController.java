package Main.GUIControllers;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

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
    void initialize() throws IOException {
        loadSelectedRoom();
        loadTotalPrice();
        loadServices();
        loadServicesStack();
    }

    public void loadTotalPrice(){
        totalText.setText(HotelManagement.getTotalPrice() + " $");
    }

    public void loadSelectedRoom() throws IOException {
        vstack.getChildren().clear();
        for (Room r: HotelManagement.selectedRoom
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Bill_Item.fxml"));
            Parent bill_itemPane = fxmlLoader.load();
            Bill_ItemController bill_itemController = fxmlLoader.getController();
            bill_itemController.setRoom(r);
            bill_itemController.setRoomLabel(r.getID());
            bill_itemController.setPriceLabel(String.valueOf(r.getPrice()));
            vstack.getChildren().add(bill_itemPane);
        }
    }

    @FXML
    void confirm(ActionEvent event) {

    }

    void loadServicesStack() throws IOException {
        serviceStack.getChildren().clear();
        for (Use use: HotelManagement.selectedUse
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ServicePane.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServicePaneController servicePaneController = fxmlLoader.getController();
            servicePaneController.setPriceLabel(HotelManagement.moneyFormat(String.valueOf(use.getTotalPrice())));
            servicePaneController.setNameLabel(use.getService().getName());
            servicePaneController.setAmountLabel(String.valueOf(use.getAmount()));
            serviceStack.getChildren().add(servicePane);
        }
    }

    void loadServices() throws IOException {
        for (Service service: HotelManagement.services
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ServiceButton.fxml"));
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
