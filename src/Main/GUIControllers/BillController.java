package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class BillController {
    @FXML
    private ScrollPane listOfPane;

    @FXML
    private VBox vstack;

    @FXML
    private Text totalText;

    @FXML
    void initialize() throws IOException {
        loadSelectedRoom();
        loadTotalPrice();
    }

    public void loadTotalPrice(){
        totalText.setText(String.valueOf(HotelManagement.getTotalPrice()) + " $");
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
            vstack.getChildren().add(bill_itemPane);
        }
    }

    @FXML
    void confirm(ActionEvent event) {

    }

    @FXML
    void back(MouseEvent event) {
       close();
    }
    public void close(){
        Navigation.getRoomPickController().slideHideBill();
    }

}
