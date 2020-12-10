package Main.GUIControllers.Manager.Owner;

import Main.GUIControllers.Manager.Employee.EmployeePaneController;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import Main.Models.Owner;
import Main.Models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditOwnerController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vStack;

    @FXML
    private TextField phoneTextField;

    @FXML
    private void initialize() {
    }

    protected void loadStack(Owner owner) throws IOException, SQLException {
        vStack.getChildren().clear();
        if (owner != null){
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Owner/OwnerPane.fxml"));
            Parent parent = fxmlLoader.load();
            OwnerPaneController ownerPaneController = fxmlLoader.getController();
            ownerPaneController.setOwner(owner);
            ownerPaneController.setId(String.valueOf(owner.getId()));
            ownerPaneController.setName(owner.getName());
            ownerPaneController.setPhone(owner.getPhone());
            ownerPaneController.setType(owner.getType());

            vStack.getChildren().add(parent);
        }
        else {
            vStack.getChildren().add(new Text("There nothing to show"));
        }
    }


    @FXML
    void searchByEnter(KeyEvent event) throws SQLException, IOException {
        if (event.getCode().equals(KeyCode.ENTER)){
            search();
        }
    }

    protected void search() throws SQLException, IOException {
        loadStack(ManagerManagement.getOwnerByPhone(phoneTextField.getText()));
    }

    protected void clearStack(){
        vStack.getChildren().clear();
    }
}
