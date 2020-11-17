package Main.GUIControllers;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;

public class ServiceButtonController{
    public Service service;

    @FXML
    private ToggleButton button;

    @FXML
    void selected(ActionEvent event) {
        if (button.isSelected()){
            HotelManagement.updateSelectedUses(new Use(service,1,Navigation.getInformationFormController().getSelectedRoom()));
            button.setStyle("-fx-background-color: rgba(0,0,0,0.1) ; -fx-background-radius: 20");
        }
        else {
            HotelManagement.updateSelectedUses(new Use(service,0,Navigation.getInformationFormController().getSelectedRoom()));
            button.setStyle("-fx-background-color: rgba(255,255,255,1) ; -fx-background-radius: 20");
        }
    }
}
