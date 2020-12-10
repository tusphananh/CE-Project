package Main.GUIControllers.Manager.Employee;

import Main.Models.Navigation;
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

public class EmployeePaneController {
    private User user;

    @FXML
    private ImageView imageView;

    @FXML
    private Label name;

    @FXML
    private Label role;

    @FXML
    void edit(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Employee/Detail.fxml"));
        Parent parent = fxmlLoader.load();
        DetailController detailController = fxmlLoader.getController();
        detailController.setIdText(String.valueOf(user.getId()));
        detailController.setUsername(user.getUsername());
        detailController.setPassword(user.getPassword());
        detailController.setName(user.getName());
        detailController.setIdentifier(user.getIdentifier());
        detailController.setPhone(user.getPhone());
        detailController.setDob(user.getDob());
        detailController.setRole(user.getRole());
        detailController.setImage(user.getImage());
        detailController.setStatus(user.getStatus());

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

    public void setRole(String role) {
        this.role.setText(role);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
