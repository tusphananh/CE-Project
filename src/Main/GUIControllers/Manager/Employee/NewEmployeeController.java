package Main.GUIControllers.Manager.Employee;

import Main.Models.BanquetBookingManagement;
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

public class NewEmployeeController {
    @FXML
    private TextField username,name,password,identifier,phone,dob,role,image,status;

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
        if (username.getText().isEmpty() || name.getText().isEmpty() ||password.getText().isEmpty() ||identifier.getText().isEmpty()
                ||phone.getText().isEmpty() ||dob.getText().isEmpty() ||role.getText().isEmpty()  || status.getText().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Cant contain null value");
            alert.showAndWait();
        }
        else {

            ManagerManagement.addUser(name.getText(),phone.getText(),identifier.getText(),username.getText(),
                    dob.getText(),password.getText(),role.getText(),image.getText(),status.getText());
            Navigation.getManagerController().getEditEmployeeController().loadFlowPane();
            close(actionEvent);
        }
    }

    void setTextFieldFocus(){
        username.setFocusTraversable(false);
        name.setFocusTraversable(false);
        password.setFocusTraversable(false);
        identifier.setFocusTraversable(false);
        phone.setFocusTraversable(false);
        dob.setFocusTraversable(false);
        role.setFocusTraversable(false);
        image.setFocusTraversable(false);
        status.setFocusTraversable(false);
    }

}
