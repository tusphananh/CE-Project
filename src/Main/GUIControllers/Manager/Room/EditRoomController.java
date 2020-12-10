package Main.GUIControllers.Manager.Room;

import Main.GUIControllers.Manager.Employee.EmployeePaneController;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.User;
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

public class EditRoomController {
    ArrayList<Room> rooms = new ArrayList<>();

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    @FXML
    void initialize() throws IOException, SQLException {
        loadFlowPane();
    }

    @FXML
    void addRoom(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Room/NewRoom.fxml"));
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

    void loadRooms() throws SQLException {
        rooms.clear();
        rooms.addAll(ManagerManagement.getRooms());
    }


    protected void loadFlowPane() throws IOException, SQLException {
        loadRooms();
        flowPane.getChildren().clear();
        for (Room room: rooms
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Room/RoomPane.fxml"));
            Parent parent = fxmlLoader.load();
            RoomPaneController roomPaneController = fxmlLoader.getController();
            roomPaneController.setName(room.getName());
            roomPaneController.setRoom(room);

            try {
                roomPaneController.setImageView(new Image(getClass().getResourceAsStream("/images/" + room.images)));
            }
            catch (Exception e){
                System.out.println(room.getId() + " missing image " + room.images);
            }
            flowPane.getChildren().add(parent);
        }
    }
}
