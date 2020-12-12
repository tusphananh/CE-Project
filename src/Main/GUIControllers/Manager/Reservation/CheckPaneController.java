package Main.GUIControllers.Manager.Reservation;

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
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CheckPaneController {
    private Reservation reservation;

    @FXML
    private Text firstID;

    @FXML
    private Text lastID;

    @FXML
    private Text name;

    @FXML
    private Label status;

    @FXML
    private ImageView noteImage;

    @FXML
    void edit(ActionEvent event) throws Exception {
        if (reservation.getFirst().equals("RB")){
            showRBDetail(ManagerManagement.getRBbyRes(reservation),event);
        }
        else {
            showBBDetail(ManagerManagement.getBBbyRes(reservation),event);
        }
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

    public void setStatus(String status) {
        if (status.equals("success")){
            this.status.setTextFill(Paint.valueOf("green"));
        }
        else if (status.equals("fail")){
            this.status.setTextFill(Paint.valueOf("red"));
        }
        else if (status.equals("pending")){
            this.status.setTextFill(Paint.valueOf("cyan"));
        }
        else {
            this.status.setTextFill(Paint.valueOf("orange"));
        }
        this.status.setText(status);
    }

    public void setNoteImage(Image noteImage) {
        this.noteImage.setImage(noteImage);
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    private void showRBDetail(RoomBooking reservation,ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Reservation/RBDetail.fxml"));
        Parent parent = fxmlLoader.load();
        RBDetailController detailController = fxmlLoader.getController();
        detailController.setReservation(reservation);
        detailController.setIdText(reservation.getFirst() + " " + reservation.getId());
        detailController.setNameText(reservation.getOwner().getName());
        detailController.setPhoneText(reservation.getOwner().getPhone());
        detailController.setDateText(reservation.getFrom() + " to " + reservation.getTo());
        detailController.setStatusText(reservation.getStatus());
        detailController.setTotalText(RoomBookingManagement.moneyFormat(String.valueOf(reservation.getTotalPrice())));
        detailController.setNoteText(reservation.getNote());
        detailController.setPaymentstatusText(reservation.getPaymentStatus());
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

    private void showBBDetail(BanquetBooking reservation,ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Reservation/BBDetail.fxml"));
        Parent parent = fxmlLoader.load();
        BBDetailController detailController = fxmlLoader.getController();
        detailController.setReservation(reservation);
        detailController.setIdText(reservation.getFirst() + " " + reservation.getId());
        detailController.setNameText(reservation.getOwner().getName());
        detailController.setPhoneText(reservation.getOwner().getPhone());
        detailController.setDateText(reservation.getFrom() + " " + reservation.getHour() + "h");
        detailController.setStatusText(reservation.getStatus());
        detailController.setTotalText(RoomBookingManagement.moneyFormat(String.valueOf(reservation.getTotalPrice())));
        detailController.setNoteText(reservation.getNote());
        detailController.setPaymentstatusText(reservation.getPaymentStatus());
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
}
