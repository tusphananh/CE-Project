package Main.GUIControllers;

import Main.Models.HotelManagement;
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

public class Bill_ItemController {
    private Room room;
    @FXML
    private Label roomLabel;

    @FXML
    private FlowPane serviceFlowPane;

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
    void delete(ActionEvent event) {

    }

    @FXML
    void showDetail(ActionEvent event) {

    }

    private void loadServices() throws IOException {
        for (Service s: HotelManagement.services
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ServiceButton.fxml"));
            ToggleButton button = fxmlLoader.load();
            ServiceButtonController serviceButtonController = fxmlLoader.getController();
            serviceButtonController.service = s;
            serviceButtonController.room = room;
            button.setText(s.getName());
            serviceFlowPane.getChildren().add(button);
        }
    }

    public void setRoom(Room room) throws IOException {
        this.room = room;
        loadServices();
    }

    public Room getRoom() {
        return room;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel .setText("Room " + roomLabel);
    }
}
