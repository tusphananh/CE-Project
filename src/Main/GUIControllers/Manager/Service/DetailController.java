package Main.GUIControllers.Manager.Service;

import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class DetailController {

    @FXML
    private Label idText;

    @FXML
    private TextField name,permission,price;

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
        if (name.getText().isEmpty()||price.getText().isEmpty() ||permission.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Cant contain null value");
            alert.showAndWait();
        }
        else {
            ManagerManagement.updateService(Integer.parseInt(idText.getText()),name.getText(),Double.parseDouble(price.getText()),permission.getText());
            Navigation.getManagerController().getEditServiceController().loadFlowPane();
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
            ManagerManagement.deleteService(Integer.parseInt(idText.getText()));
            Navigation.getManagerController().getEditServiceController().loadFlowPane();
            close(actionEvent);
        }
    }

    void setTextFieldFocus(){
        name.setFocusTraversable(false);
        price.setFocusTraversable(false);
        permission.setFocusTraversable(false);
    }

    public void setIdText(String idText) {
        this.idText.setText(idText);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setPermission(String permission) {
        this.permission.setText(permission);
    }
}
