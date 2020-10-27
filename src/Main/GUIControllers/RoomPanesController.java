package Main.GUIControllers;

import Main.Models.Navigation;
import Main.Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RoomPanesController {
    private Image image;
    private Room room;

    @FXML
    private Text idText;

    @FXML
    private Text capacityText;

    @FXML
    private Text priceText;

    @FXML
    private Text typeText;

    @FXML
    private Circle circle;
    @FXML
    private Text saleText;
    @FXML
    private Circle saleCircle;


    @FXML
    public void showInformationForm(ActionEvent actionEvent){
        InformationFormController.setImageView(this.image);
        InformationFormController.setSelectedRoom(room);
        BillController.setCapacity(capacityText.getText());
        BillController.setRoomID(idText.getText());
        System.out.println(idText.getText());
        Navigation.showInformationForm(actionEvent);
    }

    public void setIdText(String idText) {
        this.idText.setText("ID:  " + idText);
    }

    public void setCapacityText(String capacityText) {
        this.capacityText.setText("Capacity:  " + capacityText);
    }

    public void setPriceText(String priceText) {
        this.priceText.setText("Price:  " + priceText + " $");
    }

    public void setTypeText(String typeText) {
        this.typeText.setText("Type:  " + typeText);
    }

    public void setImageView(Image image) {
        this.image = image;
        circle.setFill(new ImagePattern(image));
    }

    public void setSaleText(String saleText) {
        this.saleText.setText(saleText + "%"); ;
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
