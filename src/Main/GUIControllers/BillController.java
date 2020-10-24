package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BillController {

    private static String from,to,roomID,capacity;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text nameText;

    @FXML
    private Text phoneText;

    @FXML
    private Text idText;

    @FXML
    private Text fromText;

    @FXML
    private Text toText;

    @FXML
    private Text roomIDText;

    @FXML
    private Text capacityText;

    @FXML
    private Text totalText;


    @FXML
    public void initialize() throws IOException {
        Navigation.fadeOut(mainPane,300);
        setRoomIdText(roomID);
        setCapacityText(capacity);
        setFromText(from);
        setToText(to);
    }

    @FXML
    public void close(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    public void setIdText(String idText) {
        this.idText.setText("ID : " + idText);
    }

    public void setNameText(String nameText) {
        this.nameText.setText("Name : " + nameText);
    }

    public void setPhoneText(String phoneText) {
        this.phoneText.setText("Phone : " + phoneText);
    }

    public void setFromText(String fromText) {
        this.fromText.setText("From : " + fromText);

    }

    public void setRoomIdText(String idText) {
        this.roomIDText.setText( idText);
    }

    public void setTotalText(String totalText) {
        this.totalText.setText(totalText + " $");
    }

    public void setCapacityText(String capacityText) {
        this.capacityText.setText(capacityText);
    }

    public void setToText(String toText) {
        this.toText.setText("To : " + toText);
    }

    public static void setCapacity(String capacity) {
        BillController.capacity = capacity;
    }

    public static void setFrom(String from) {
        BillController.from = from;
    }

    public static void setTo(String to) {
        BillController.to = to;
    }

    public static void setRoomID(String roomID) {
        BillController.roomID = roomID;
    }
}
