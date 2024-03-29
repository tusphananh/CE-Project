package Main.GUIControllers.Manager.Service;

import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NewServiceController {


    @FXML
    private TextField name,price,permission;

    @FXML
    private void initialize(){
        setTextFieldFocus();
    }

    @FXML
    void close(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void confirm(ActionEvent actionEvent) throws SQLException, IOException {
        if (name.getText().isEmpty() || price.getText().isEmpty() ||permission.getText().isEmpty()){
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(primaryStage);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Cant contain null value");
            alert.showAndWait();
        }
        else {
            ManagerManagement.addService(name.getText(),Double.parseDouble(price.getText()),permission.getText());
            Navigation.getManagerController().getEditServiceController().loadFlowPane();
            close(actionEvent);
        }
    }

    void setTextFieldFocus(){
        name.setFocusTraversable(false);
        price.setFocusTraversable(false);
        permission.setFocusTraversable(false);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

}
