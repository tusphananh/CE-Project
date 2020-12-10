package Main.GUIControllers.Manager.Reservation;

import Main.Models.BanquetBookingManagement;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import Main.Models.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class BBDetailController {
    private Reservation reservation;

    @FXML
    private Label idText;

    @FXML
    private Label nameText;

    @FXML
    private Label phoneText;

    @FXML
    private Label dateText;

    @FXML
    private Label totalText;

    @FXML
    private TextField statusTextField,paymentStatusTextField,noteTextField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vstack;

    @FXML
    private void initialize(){
        statusTextField.setFocusTraversable(false);
        paymentStatusTextField.setFocusTraversable(false);
        noteTextField.setFocusTraversable(false);
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void confirm(ActionEvent actionEvent) throws SQLException, IOException {
        if (!statusTextField.getText().isEmpty() || !paymentStatusTextField.getText().isEmpty()){
            ManagerManagement.editReservation(reservation.getFirst(),reservation.getId(),statusTextField.getText(),paymentStatusTextField.getText(),noteTextField.getText());
        }
        Navigation.getManagerController().getEditReservationController().updateStack();
        close(actionEvent);
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

    public void setDateText(String dateText) {
        this.dateText.setText(dateText);
    }

    public void setTotalText(String totalText) {
        this.totalText.setText(totalText);
    }

    public void setStatusText(String statusText) {
        this.statusTextField.setText(statusText);
    }

    public void setNoteText(String noteText) {
        this.noteTextField.setText(noteText);
    }

    public void addStack(Parent parent){
        vstack.getChildren().add(parent);
    }

    public void setPaymentstatusText(String paymentstatusText) {
        this.paymentStatusTextField.setText(paymentstatusText);
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
