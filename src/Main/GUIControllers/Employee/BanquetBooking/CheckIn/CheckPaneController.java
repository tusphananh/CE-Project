package Main.GUIControllers.Employee.BanquetBooking.CheckIn;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import java.util.Optional;

public class CheckPaneController {
    private BanquetBooking reservation;

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
    private ImageView noteImage,confirmImageView,cancelImageView,detailImageView;

    @FXML
    void initialize(){
    }

    @FXML
    void cancel(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("Confirm");
        alert.setContentText("Do you want do cancel this reservation");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK){
            BanquetBookingManagement.updateStatus("fail","fail",reservation.getId());
            Navigation.getBanquetCheckInController().loadStack();
        }
    }

    @FXML
    void confirm(ActionEvent event) throws Exception {
        BanquetBookingManagement.updateStatus("success","success",reservation.getId());
        Navigation.getBanquetCheckInController().loadStack();
    }

    @FXML
    void detail(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/CheckIn/Detail.fxml"));
        Parent parent = fxmlLoader.load();
        DetailController detailController = fxmlLoader.getController();
        detailController.setIdText(String.valueOf(reservation.getId()));
        detailController.setNameText(reservation.getOwner().getName());
        detailController.setPhoneText(reservation.getOwner().getPhone());
        detailController.setDateText(reservation.getFrom() + " " + reservation.getHour() + "h");
        detailController.setStatusText(reservation.getPaymentStatus());
        detailController.setTotalText(RoomBookingManagement.moneyFormat(String.valueOf(reservation.getTotalPrice())));
        detailController.setNoteText(reservation.getNote());
        for (Use use: reservation.getUses()
        ) {
            HBox hBox = new HBox();
            hBox.setSpacing(20);
            hBox.getChildren().add(new Label(String.valueOf(use.getAmount())));
            hBox.getChildren().add(new Label(use.getService().getName()));
            detailController.addStack(hBox);
        }

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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(BanquetBooking reservation) {
        this.reservation = reservation;
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
