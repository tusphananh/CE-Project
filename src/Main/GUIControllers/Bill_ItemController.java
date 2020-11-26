package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.NavigableMap;

public class Bill_ItemController {
    private Room room;
    @FXML
    private Label roomLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Button detailButton;

    @FXML
    private Button deleteButton;


    @FXML
    private ImageView detailImageView,deleteImageView;

    @FXML
    void initialize() throws IOException {
        detailImageView.setImage(new Image(getClass().getResourceAsStream("/images/detail.png")));
        deleteImageView.setImage(new Image(getClass().getResourceAsStream("/images/drop.png")));
    }

    @FXML
    void delete(ActionEvent event) throws Exception {
        HotelManagement.dropSelectedRoom(room);
        Navigation.getBillController().loadSelectedRoom();
        if (HotelManagement.selectedRoom.isEmpty()){
            HotelManagement.selectedUse.clear();
            Navigation.getBillController().close();
        }
        Navigation.getBillController().loadTotalPrice();
        Navigation.getRoomPickController().updateBasketButton();
    }

    @FXML
    void showDetail(ActionEvent event) {
    }

    public void setRoom(Room room) throws IOException {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel .setText("Room " + roomLabel);
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel.setText(priceLabel + " $");
    }
}
