package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BillController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    public void initialize(){
        Navigation.fadeOut(mainPane,300);
    }

    @FXML
    public void close(ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }
}
