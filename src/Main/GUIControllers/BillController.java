package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class BillController {
    @FXML
    private ScrollPane listOfPane;

    @FXML
    private VBox vstack;

    @FXML
    private Text totalText;

    @FXML
    void confirm(ActionEvent event) {

    }

    @FXML
    void back(MouseEvent event) {
        Navigation.getEmployeeController().slideHideBill();
    }

}
