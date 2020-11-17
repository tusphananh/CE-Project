package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;

public class RoomPanesController {
    private Image image;
    private Room room;

    @FXML
    private Label idText;
    @FXML
    private Label capacityText;
    @FXML
    private Label priceText;
    @FXML
    private Label typeText;
    @FXML
    private Circle circle;
    @FXML
    private Text saleText;
    @FXML
    private Circle saleCircle;
    @FXML
    private Circle statusCircle;
    @FXML
    private ImageView addImage,dropImage;

    @FXML
    void initialize(){
        addImage.setImage(new Image(getClass().getResourceAsStream("/images/add.png")));
        dropImage.setImage(new Image(getClass().getResourceAsStream("/images/drop.png")));
    }

    @FXML
    public void reserve(ActionEvent actionEvent) throws Exception {
        HotelManagement.addSelectedRoom(room);
        updateBasketButton();
    }

    @FXML
    public void drop(ActionEvent actionEvent) throws Exception {
        HotelManagement.dropSelectedRoom(room);
        updateBasketButton();
    }

    public void updateBasketButton(){
        if (HotelManagement.selectedRoom.isEmpty()){
            Navigation.getRoomPickController().setBasketButtonContent("",false);
        }
        else {
            Navigation.getRoomPickController().setBasketButtonContent(HotelManagement.selectedRoom.size() + " ITEM",true);
        }
    }


    public void setIdText(String idText) {
        this.idText.setText(idText);
    }

    public void setCapacityText(String capacityText) {
        this.capacityText.setText(capacityText);
    }

    public void setPriceText(String priceText) {
        this.priceText.setText(HotelManagement.moneyFormat(priceText));
    }

    public void setTypeText(String typeText) {
        this.typeText.setText(typeText);
    }

    public void setImageView(Image image) {
        this.image = image;
        circle.setFill(new ImagePattern(image));
    }

    public void setSaleText(String saleText) {
        this.saleText.setText(saleText + "%");
    }

    public void showSale(){
        saleText.setVisible(true);
        saleCircle.setVisible(true);
        saleText.setRotate(45);
    }
    public void hideSale(){
        saleText.setVisible(false);
        saleCircle.setVisible(false);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
