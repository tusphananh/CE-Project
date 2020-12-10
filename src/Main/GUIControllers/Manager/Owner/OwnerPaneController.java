package Main.GUIControllers.Manager.Owner;

import Main.GUIControllers.Manager.Reservation.BBDetailController;
import Main.GUIControllers.Manager.Reservation.RBDetailController;
import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class OwnerPaneController {
    private Owner owner;

    @FXML
    private Text id;

    @FXML
    private Text name;

    @FXML
    private Text phone;

    @FXML
    private Label type;

    @FXML
    void edit(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Owner/Detail.fxml"));
        Parent parent = fxmlLoader.load();
        DetailController detailController = fxmlLoader.getController();
        detailController.setIdText(String.valueOf(owner.getId()));
        detailController.setName(owner.getName());
        detailController.setPhone(owner.getPhone());
        detailController.setCoin(String.valueOf(owner.getCoins()));
        detailController.setType(owner.getType());

        Scene scene = new Scene(parent);
        scene.getStylesheets().add("Main/StyleCSS/StageStyle.css");
        scene.setFill(Color.TRANSPARENT);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initOwner(primaryStage);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public void setId(String id) {
        this.id.setText(id);
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
}
