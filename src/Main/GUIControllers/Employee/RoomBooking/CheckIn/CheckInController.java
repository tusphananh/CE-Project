package Main.GUIControllers.Employee.RoomBooking.CheckIn;

import Main.Models.RoomBookingManagement;
import Main.Models.Navigation;
import Main.Models.RoomBooking;
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
        for (RoomBooking r: RoomBookingManagement.getPendingReservation()
             ) {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/CheckIn/CheckPane.fxml"));
            Parent parent = loader.load();
            CheckPaneController checkPaneController = loader.getController();
            checkPaneController.setNameText(r.getOwner().getName());
            checkPaneController.setPhoneText(r.getOwner().getPhone());
            checkPaneController.setReservation(r);
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

    public void loadStack() throws Exception {
        loadCheckPane();
        Navigation.fadeOut(vstack,500);
    }
}
