package Main.GUIControllers.Employee.BanquetBooking.CheckIn;

import Main.Models.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public class CheckInController {
    @FXML
    private ScrollPane listOfPane;

    @FXML
    private VBox vstack;

    @FXML
    void initialize() throws Exception {
        loadStack();
    }

    void loadCheckPane() throws Exception {
        vstack.getChildren().clear();
        for (BanquetBooking r: BanquetManagement.getPendingReservation()
             ) {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/CheckIn/CheckPane.fxml"));
            Parent parent = loader.load();
            CheckPaneController checkPaneController = loader.getController();
            checkPaneController.setNameText(r.getOwner().getName());
            checkPaneController.setPhoneText(r.getOwner().getPhone());
            checkPaneController.setRoomText(r.getHour() + "h");
            checkPaneController.setReservation(r);
            if (!r.getNote().equals("")){
                checkPaneController.setNoteImage(new Image(getClass().getResourceAsStream("/images/note.png")));
            }
            vstack.getChildren().add(parent);
        }
    }

    public void loadStack() throws Exception {
        loadCheckPane();
        Navigation.fadeOut(vstack,500);
    }
}
