package Main.GUIControllers.Employee.Service;

import Main.GUIControllers.Manager.Reservation.BBDetailController;
import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CheckPaneController {
    private RoomBooking reservation;

    @FXML
    private Text firstID;

    @FXML
    private Text lastID;

    @FXML
    private Text name;

    @FXML
    private ImageView noteImage;

    @FXML
    void choose(ActionEvent event) throws Exception {
        Navigation.getServicePickController().setSelectedRoomBooking(reservation);
        Navigation.getServicePickController().loadChosenText();
    }

    public void setFirstID(String firstID) {
        this.firstID.setText(firstID);
    }

    public void setLastID(String lastID) {
        this.lastID.setText(lastID);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setNoteImage(Image noteImage) {
        this.noteImage.setImage(noteImage);
    }

    public void setReservation(RoomBooking reservation) {
        this.reservation = reservation;
    }

}
