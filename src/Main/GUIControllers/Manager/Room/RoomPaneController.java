package Main.GUIControllers.Manager.Room;

import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RoomPaneController {
    private Room room;

    @FXML
    private ImageView imageView;

    @FXML
    private Label name;

    @FXML
    void edit(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Room/Detail.fxml"));
        Parent parent = fxmlLoader.load();
        DetailController detailController = fxmlLoader.getController();
        detailController.setIdText(String.valueOf(room.getId()));
        detailController.setName(room.getName());
        detailController.setStatus(room.getStatus());
        detailController.setType(room.getType());
        detailController.setCapacity(String.valueOf(room.getCapacity()));
        detailController.setPrice(String.valueOf(room.getPrice()));
        detailController.setSale(String.valueOf(room.getSale()));
        detailController.setImage(room.getImages());

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

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setImageView(Image imageView) {
        this.imageView.setImage(imageView);
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
