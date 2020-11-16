package Main.GUIControllers;

import Main.Models.*;
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
import java.util.ArrayList;

public class InformationFormController {
    private double x,y;
    private static Image image;
    private Room selectedRoom;

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
        mainPane.setOpacity(0);
        Navigation.fadeOut(mainPane,300);
        for (Service s: HotelManagement.services
             ) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/ServiceButton.fxml"));
            ToggleButton button = fxmlLoader.load();
            ServiceButtonController serviceButtonController = fxmlLoader.getController();
            serviceButtonController.service = s;
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


    public void setImageView(Image image) {
        InformationFormController.image = image;
        circle.setFill(new ImagePattern(image));
    }

    @FXML
    private void confirm(ActionEvent actionEvent) throws Exception {
        HotelManagement.addSelectedRoom(selectedRoom);
        refresh();
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

    void refresh(){
        setSelectedRoom(null);
    }

    public void updateBasketButton() throws IOException {
        if (HotelManagement.selectedRoom.isEmpty()){
            Navigation.getEmployeeController().setBasketButtonContent("",false);
        }
        else {
            Navigation.getEmployeeController().setBasketButtonContent(HotelManagement.selectedRoom.size() + " ITEM",true);
        }
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }
}
