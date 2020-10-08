package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Stack;

public class Navigation
{
    static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    private static Scene managerScene, employeeScene;

    public static void navigateNewManager(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        managerScene = new Scene( FXMLLoader.load(Navigation.class.getResource("ManagerUI.fxml")));
        primaryStage.setScene(managerScene);
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);

    }
    public static void navigateNewEmployee(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        employeeScene = new Scene( FXMLLoader.load(Navigation.class.getResource("EmployeeUI.fxml")));
        primaryStage.setScene(employeeScene);
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
    }

    public static void navigateManager(javafx.event.ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(managerScene);
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);

    }
    public static void navigateLogin(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene( FXMLLoader.load(Navigation.class.getResource("Login.fxml"))));
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
    }

    public static void navigateEmployee(javafx.event.ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(employeeScene);
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
    }

    public static void minimize(javafx.event.ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }

    public static void showInformationForm(ActionEvent actionEvent){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("InformationForm.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(parent));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
