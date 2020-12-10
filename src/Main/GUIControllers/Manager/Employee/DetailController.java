package Main.GUIControllers.Manager.Employee;

import Main.Models.BanquetBookingManagement;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class DetailController {

    @FXML
    private Label idText;

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

            ManagerManagement.updateUser(Integer.parseInt(idText.getText()),name.getText(),phone.getText(),identifier.getText(),username.getText(),
                    dob.getText(),password.getText(),role.getText(),image.getText(),status.getText());
            Navigation.getManagerController().getEditEmployeeController().loadFlowPane();
            close(actionEvent);
        }
    }

    @FXML
    void delete(ActionEvent actionEvent) throws SQLException, IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleter");
        alert.setHeaderText("Confirm");
        alert.setContentText("Do you want do delete this User");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK){
            ManagerManagement.deleteUser(Integer.parseInt(idText.getText()));
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

    public void setIdText(String idText) {
        this.idText.setText(idText);
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public void setIdentifier(String identifier) {
        this.identifier.setText(identifier);
    }

    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    public void setDob(String dob) {
        this.dob.setText(dob);
    }

    public void setRole(String role) {
        this.role.setText(role);
    }

    public void setImage(String image) {
        this.image.setText(image);
    }

    public void setStatus(String status) {
        this.status.setText(status);;
    }
}
