package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class RoomPanesController {

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
    public void showInformationForm(ActionEvent actionEvent){
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

    public void setTypeText(Type typeText) {
        this.typeText.setText("Type:  " + typeText.getValue());
    }

    public void setImageView(Image image) {
        circle.setStroke(Color.TRANSPARENT);
        circle.setFill(new ImagePattern(image));
    }
}
