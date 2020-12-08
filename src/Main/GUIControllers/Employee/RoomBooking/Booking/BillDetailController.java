package Main.GUIControllers.Employee.RoomBooking.Booking;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BillDetailController {

    @FXML
    private Label roomIDLabel;

    @FXML
    private Label capacityLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView roomImageView;

    @FXML
    void back(ActionEvent event) {
        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    public void setCapacityLabel(String capacityLabel) {
        this.capacityLabel.setText("Capacity : " + capacityLabel);
    }
    public void setRoomIDLabel(String roomIDLabel) {
        this.roomIDLabel.setText("Room ID : " + roomIDLabel);
    }
    public void setTypeLabel(String typeLabel) {
        this.typeLabel.setText("Type : " + typeLabel);
    }
    public void setPriceLabel(String priceLabel) {
        this.priceLabel.setText("Price : " + priceLabel);
    }

    public void setRoomImageView(Image roomImage) {
        this.roomImageView.setImage(roomImage);
    }
}
