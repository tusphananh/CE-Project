package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.Service;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class InformationFormController {
    private double x,y;
    private static Image image;
    private static Room selectedRoom;
    private EmployeeController employeeController;

    public static void setSelectedRoom(Room selectedRoom) {
        InformationFormController.selectedRoom = selectedRoom;
    }

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane firstPane,secondPane;

    @FXML
    private FlowPane flowPane;

    @FXML
    private HBox navigationPane;

    @FXML
    private Circle circle;

    @FXML
    public void initialize() throws IOException {
        circle.setFill(new ImagePattern(image));
        for (Service s: HotelManagement.services
             ) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            ToggleButton button = fxmlLoader.load(getClass().getResource("../fxml/ServiceButton.fxml").openStream());
            ServiceButtonController serviceButtonController = fxmlLoader.getController();
            serviceButtonController.service = s;
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (button.isSelected()){
                        selectedRoom.addService(s);
                        button.setStyle("-fx-background-color: rgba(0,0,0,0.2);-fx-background-radius: 30");
                    }
                    else {
                        selectedRoom.dropService(s);
                        button.setStyle("-fx-background-color: rgba(255,255,255,1);-fx-background-radius: 30");
                    }
                }
            });
            button.setText(s.getName());
            flowPane.getChildren().add(button);
        }

    }

    @FXML
    public void exit(MouseEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }
    @FXML
    public void mousePressed(MouseEvent mouseEvent){
        x = mouseEvent.getSceneX();
        y = mouseEvent.getSceneY();
    }

    @FXML
    public void mouseDraged(MouseEvent mouseEvent){
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setX(mouseEvent.getScreenX() - x );
        stage.setY(mouseEvent.getScreenY() - y );
    }


    public static void setImageView(Image image) {
        InformationFormController.image = image;
    }

    @FXML
    private void confirm(ActionEvent actionEvent) throws Exception {
        HotelManagement.addSelectedRoom(selectedRoom,Navigation.employeeController.getFromTextField(),Navigation.employeeController.getToTextField());
        updateBasketButton();
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(200));
        fadeTransition.setNode(mainPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.close();
            }
        });
        fadeTransition.play();
    }

    public void updateBasketButton() throws IOException {
        if (HotelManagement.selectedRoom.isEmpty()){
            Navigation.employeeController.setBasketButtonContent("",false);
        }
        else {
            Navigation.employeeController.setBasketButtonContent(HotelManagement.selectedRoom.size() + " ITEM",true);
        }
    }
}
