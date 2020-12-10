package Main.GUIControllers.Manager.Service;

import Main.GUIControllers.Manager.Room.RoomPaneController;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditServiceController {
    ArrayList<Service> services = new ArrayList<>();

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    @FXML
    void initialize() throws IOException, SQLException {
        loadFlowPane();
    }

    @FXML
    void addService(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Service/NewService.fxml"));
        Parent parent = fxmlLoader.load();

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

    void loadServices() throws SQLException {
        services.clear();
        services.addAll(ManagerManagement.getServices());
    }


    protected void loadFlowPane() throws IOException, SQLException {
        loadServices();
        flowPane.getChildren().clear();
        for (Service service: services
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Service/ServicePane.fxml"));
            Parent parent = fxmlLoader.load();
            ServicePaneController servicePaneController = fxmlLoader.getController();
            servicePaneController.setService(service);
            servicePaneController.setName(service.getName());
            servicePaneController.setPermission(service.getPermission());

            flowPane.getChildren().add(parent);
        }
    }
}
