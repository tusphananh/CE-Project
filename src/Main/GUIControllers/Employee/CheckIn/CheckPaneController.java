package Main.GUIControllers.Employee.CheckIn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class CheckPaneController {
    @FXML
    private Text nameText;

    @FXML
    private Text phoneText;

    @FXML
    private Text roomText;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Circle statusCircle;

    @FXML
    private ImageView noteImage;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void confirm(ActionEvent event) {

    }

    @FXML
    void detail(ActionEvent event) {

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

    public void setNoteImage(Image noteImage) {
        this.noteImage.setImage(noteImage);
    }
}
