package Main.GUIControllers.Manager.Employee;

import Main.Models.BanquetBookingManagement;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
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

public class EditEmployeeController {
    ArrayList<User> users = new ArrayList<>();

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private FlowPane flowPane;

    @FXML
    private void initialize() throws SQLException, IOException {
        loadFlowPane();
    }

    @FXML
    void addEmployee(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Employee/NewEmployee.fxml"));
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
    void loadUsers() throws SQLException {
        users.clear();
        users.addAll(ManagerManagement.getUsers());
    }

    protected void loadFlowPane() throws IOException, SQLException {
        loadUsers();
        flowPane.getChildren().clear();
        for (User user: users
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Employee/EmployeePane.fxml"));
            Parent parent = fxmlLoader.load();
            EmployeePaneController employeePaneController = fxmlLoader.getController();
            employeePaneController.setUser(user);
            employeePaneController.setName(user.getName());
            employeePaneController.setRole(user.getRole());
            try {
                employeePaneController.setImageView(new Image(getClass().getResourceAsStream("/images/" + user.getImage())));
            }
            catch (Exception e){
                System.out.println(user.getId() + " missing image " + user.getImage());
            }
            flowPane.getChildren().add(parent);
        }
    }
}
