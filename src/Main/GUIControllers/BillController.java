package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BillController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    public void initialize(){
        Navigation.fadeOut(mainPane,300);
    }
}
