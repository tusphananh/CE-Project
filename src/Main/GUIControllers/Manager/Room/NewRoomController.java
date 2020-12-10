package Main.GUIControllers.Manager.Room;

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

public class NewRoomController {


    @FXML
    private TextField name,status,type,capacity,price,sale,image;

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
        if (name.getText().isEmpty() || status.getText().isEmpty() ||type.getText().isEmpty() ||capacity.getText().isEmpty()
                ||price.getText().isEmpty() ||sale.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Cant contain null value");
            alert.showAndWait();
        }
        else {
            ManagerManagement.addRoom(name.getText(),status.getText(),
                    type.getText(),Long.parseLong(capacity.getText()),Double.parseDouble(price.getText()),Double.parseDouble(sale.getText()),image.getText());
            Navigation.getManagerController().getEditRoomController().loadFlowPane();
            close(actionEvent);
        }
    }

    void setTextFieldFocus(){
        name.setFocusTraversable(false);
        status.setFocusTraversable(false);
        type.setFocusTraversable(false);
        capacity.setFocusTraversable(false);
        price.setFocusTraversable(false);
        sale.setFocusTraversable(false);
        image.setFocusTraversable(false);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setStatus(String status) {
        this.status.setText(status);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setCapacity(String capacity) {
        this.capacity.setText(capacity);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setSale(String sale) {
        this.sale.setText(sale);
    }

    public void setImage(String image) {
        this.image.setText(image);
    }
}
