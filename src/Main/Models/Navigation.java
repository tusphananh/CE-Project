package Main.Models;

import Main.GUIControllers.BillController;
import Main.GUIControllers.EmployeeController;
import Main.GUIControllers.InformationFormController;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class Navigation
{
    static Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    private static Scene managerScene, employeeScene;
    private static BillController billController;
    private static InformationFormController informationFormController;
    private static EmployeeController employeeController;
    public static void navigateNewManager(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        managerScene = new Scene( FXMLLoader.load(Navigation.class.getResource("../fxml/ManagerUI.fxml")));
        primaryStage.setScene(managerScene);
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
    }
    public static void navigateNewEmployee(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/EmployeeUI.fxml"));
        Parent parent = fxmlLoader.load();
        employeeController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth() / 1.5);
        stage.setHeight(bounds.getHeight() / 1.5);
        stage.show();
    }

    public static void navigateLogin(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene( FXMLLoader.load(Navigation.class.getResource("../fxml/Login.fxml"))));
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth())/2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight())/2);
    }

    public static void minimize(javafx.event.ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
    }


    public static void showBill(ActionEvent actionEvent,Parent parent){
        try{
            Scene scene = new Scene(parent);
            scene.getStylesheets().add("Main/StyleCSS/StageStyle.css");
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public static void fadeOut(Node node, double fadeSpeed){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(fadeSpeed));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    public static void fadeIn(Node node, double fadeSpeed){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(fadeSpeed));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    public static void slideHorizontallyTransition(Node node, double to, double duration){
        TranslateTransition swipeTransition = new TranslateTransition();
        swipeTransition.setNode(node);
        swipeTransition.setDuration(Duration.millis(duration));
        swipeTransition.setToX(to);
        swipeTransition.play();
    }

    public static void slideVerticallyTransition(Node node,double to,double duration){
        TranslateTransition swipeTransition = new TranslateTransition();
        swipeTransition.setNode(node);
        swipeTransition.setDuration(Duration.millis(duration));
        swipeTransition.setToY(to);
        swipeTransition.play();
    }

    public static BillController getBillController() {
        return billController;
    }

    public static InformationFormController getInformationFormController() {
        return informationFormController;
    }

    public static EmployeeController getEmployeeController() {
        return employeeController;
    }

    public static void setInformationFormController(InformationFormController informationFormController) {
        Navigation.informationFormController = informationFormController;
    }
}
