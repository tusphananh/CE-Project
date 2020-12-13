package Main.GUIControllers.Manager.Owner;

import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class DetailController {

    @FXML
    private Label idText;

    @FXML
    private TextField name,phone;

    @FXML
    private Label coin,type;

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
        if ( name.getText().isEmpty() ||phone.getText().isEmpty()){
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(primaryStage);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Cant contain null value");
            alert.showAndWait();
        }
        else {
            Navigation.getManagerController().getEditOwnerController().search();
            close(actionEvent);
        }
    }

    @FXML
    void delete(ActionEvent actionEvent) throws SQLException, IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(primaryStage);
        alert.setTitle("Deleter");
        alert.setHeaderText("Confirm");
        alert.setContentText("Do you want do delete this User");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK){

            Navigation.getManagerController().getEditOwnerController().clearStack();
            close(actionEvent);
        }
    }

    void setTextFieldFocus(){
        name.setFocusTraversable(false);
        phone.setFocusTraversable(false);
    }

    public void setIdText(String idText) {
        this.idText.setText(idText);
    }


    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPhone(String phone) {
        this.phone.setText(phone);
    }

    public void setType(String type) {
        if (type.equals("bronze")){
            this.type.setTextFill(Paint.valueOf("cd7f32"));
        }
        if (type.equals("silver")){
            this.type.setTextFill(Paint.valueOf("D3D3D3"));
        }
        if (type.equals("gold")){
            this.type.setTextFill(Paint.valueOf("F0E68C"));
        }
        if (type.equals("diamond")){
            this.type.setTextFill(Paint.valueOf("b9f2ff"));
        }
        this.type.setText(type);
    }

    public void setCoin(String coin) {
        this.coin.setText(coin);
    }
}
