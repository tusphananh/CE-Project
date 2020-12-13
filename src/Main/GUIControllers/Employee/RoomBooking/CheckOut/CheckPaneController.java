package Main.GUIControllers.Employee.RoomBooking.CheckOut;

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
import java.sql.SQLException;
import java.util.Optional;

public class CheckPaneController {
    private RoomBooking reservation;

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
            RoomBookingManagement.updateStatus("fail","fail",reservation.getId());
            Navigation.getCheckOutController().loadStack();
        }
    }

    @FXML
    void confirm(ActionEvent actionEvent) throws Exception {
        if (!reservation.getUses().isEmpty()){
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/CheckOut/ServiceBill.fxml"));
            Parent parent = fxmlLoader.load();
            ServiceBillController detailController = fxmlLoader.getController();
            detailController.setReservation(reservation);
            double total = 0;
            for (Use use: reservation.getUses()
            ) {
                total = total + use.getTotalPrice();
                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.getChildren().add(new Label(String.valueOf(use.getAmount())));
                hBox.getChildren().add(new Label(use.getService().getName()));
                hBox.getChildren().add(new Label(use.getTotalPrice() + " $"));
                detailController.addStack(hBox);
            }
            detailController.setTotal(String.valueOf(total));
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
        else {
            updateSuccessReservation();
        }
    }

    protected void updateSuccessReservation() throws Exception {
        RoomBookingManagement.updateStatus("success","success",reservation.getId());
        Owner owner = reservation.getOwner();
        owner.setCoins(owner.getCoins() + (int) reservation.getTotalPrice());
        RoomBookingManagement.updateCoin(owner);
        Navigation.getCheckOutController().loadStack();
    }

    @FXML
    void detail(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/CheckOut/Detail.fxml"));
        Parent parent = fxmlLoader.load();
        DetailController detailController = fxmlLoader.getController();
        detailController.setIdText(String.valueOf(reservation.getId()));
        detailController.setNameText(reservation.getOwner().getName());
        detailController.setPhoneText(reservation.getOwner().getPhone());
        detailController.setDateText(reservation.getFrom() + " to " + reservation.getTo());
        detailController.setStatusText(reservation.getPaymentStatus());
        detailController.setTotalText(RoomBookingManagement.moneyFormat(String.valueOf(reservation.getTotalPrice())));
        detailController.setNoteText(reservation.getNote());
        if (reservation.getRooms().size() > 1){
            String rooms = "";
            for (Room room: reservation.getRooms()
            ) {
                rooms = rooms + room.getName() + " , ";
            }
            detailController.setRoomText(rooms);
        }
        else {
            detailController.setRoomText(reservation.getRooms().get(0).getName());
        }
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

    public void setReservation(RoomBooking reservation) {
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
