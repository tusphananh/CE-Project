package Main.GUIControllers.Employee.Service;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SuccessController {
    @FXML
    private void exit(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().showServicePane();
    }
}
