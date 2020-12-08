package Main.GUIControllers.Employee.BanquetBooking.CheckIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DetailController {
    @FXML
    private Label idText;

    @FXML
    private Label nameText;

    @FXML
    private Label phoneText;

    @FXML
    private Label roomText;

    @FXML
    private Label dateText;

    @FXML
    private Label totalText;

    @FXML
    private Label statusText;

    @FXML
    private Label noteText;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vstack;

    @FXML
    void close(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    public void setIdText(String idText) {
        this.idText.setText(idText);
    }

    public void setNameText(String nameText) {
        this.nameText.setText(nameText);
    }

    public void setPhoneText(String phoneText) {
        this.phoneText.setText(phoneText);
    }

    public void setRoomText(String roomText) {
        this.roomText.setText(roomText);
    }

    public void setDateText(String dateText) {
        this.dateText.setText(dateText);
    }

    public void setTotalText(String totalText) {
        this.totalText.setText(totalText);
    }

    public void setStatusText(String statusText) {
        this.statusText.setText(statusText);
    }

    public void setNoteText(String noteText) {
        this.noteText.setText(noteText);
    }

    public void addStack(Parent parent){
        vstack.getChildren().add(parent);
    }
}
