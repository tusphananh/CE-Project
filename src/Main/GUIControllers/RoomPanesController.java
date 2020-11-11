package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.crypto.Cipher;
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
    void initialize(){

    }

    @FXML
    public void showInformationForm(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/InformationForm.fxml"));
        Parent parent = fxmlLoader.load();
        Navigation.setInformationFormController(fxmlLoader.getController());
        Navigation.getInformationFormController().setImageView(this.image);
        Navigation.getInformationFormController().setSelectedRoom(room);
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("Main/StyleCSS/StageStyle.css");
        Stage stage = new Stage();
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
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
