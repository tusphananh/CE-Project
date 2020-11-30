package Main.GUIControllers.Employee.CheckIn;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class CheckInController {
    @FXML
    private ScrollPane listOfPane;

    @FXML
    private VBox vstack;

    @FXML
    void initialize() throws IOException {
        loadStack();
    }

    void loadCheckPane() throws IOException {
        vstack.getChildren().clear();
        for (Reservation r: HotelManagement.pendingReservations
             ) {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/CheckIn/CheckPane.fxml"));
            Parent parent = loader.load();
            CheckPaneController checkPaneController = loader.getController();
            checkPaneController.setNameText(r.getOwner().getName());
            checkPaneController.setPhoneText(r.getOwner().getPhone());
            int amount = r.getRooms().size();
            if (amount > 1){
                checkPaneController.setRoomText(amount + " rooms");
            }
            else {
                checkPaneController.setRoomText(r.getRooms().get(0).getID());
            }
            if (!r.getNote().equals("")){
                checkPaneController.setNoteImage(new Image(getClass().getResourceAsStream("/images/note.png")));
            }
            vstack.getChildren().add(parent);
        }
    }

    void loadStack() throws IOException {
        loadCheckPane();
        Navigation.fadeOut(vstack,500);
    }
}
