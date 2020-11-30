package Main.GUIControllers.Employee.RoomBooking;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Bill_ItemController {
    private Room room;
    @FXML
    private Label roomLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label typeLabel;

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
    void showDetail(ActionEvent event) throws IOException {
        loadDetail(event);
    }

    public void setRoom(Room room) throws IOException {
        this.room = room;
    }
    private void loadDetail(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/BillDetail.fxml"));
        Parent parent = fxmlLoader.load();
        BillDetailController billDetailController = fxmlLoader.getController();
        billDetailController.setCapacityLabel(String.valueOf(room.getCapacity()));
        billDetailController.setPriceLabel(HotelManagement.moneyFormat(String.valueOf(room.getPrice())));
        billDetailController.setRoomIDLabel(room.getID());
        billDetailController.setTypeLabel(room.type);
        Scene scene = new Scene(parent);
        scene.getStylesheets().add("Main/StyleCSS/StageStyle.css");
        scene.setFill(Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
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

    public void setTypeLabel(String typeLabel) {
        this.typeLabel.setText(typeLabel);
    }
}
