package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.Service;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class InformationFormController {
    private double x,y;
    private static Image image;
    private static Room selectedRoom;

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
    public void initialize(){
        circle.setFill(new ImagePattern(image));
        for (Service s: HotelManagement.services
             ) {
            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.rgb(0,0,0,0.1));
            ToggleButton button = new ToggleButton(s.getName());
            button.setStyle("-fx-background-color: white ; -fx-background-radius: 20; -fx-font-size: 15");
            button.setEffect(dropShadow);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (!button.isSelected()){
                        button.setStyle("-fx-background-color: white ; " + "-fx-background-radius: 20;-fx-font-size: 15");
                    }
                    else {
                        button.setStyle("-fx-background-color: rgba(0,0,0,0.05) ; " + "-fx-background-radius: 20;-fx-font-size: 15");
                    }
                }
            });
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
    private void confirm(ActionEvent actionEvent) throws IOException {
        HotelManagement.selectedRoom.add(selectedRoom);
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
//        if (!HotelManagement.selectedRoom.isEmpty()){
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/EmployeeUI.fxml"));
//            Parent parent = loader.load();
//            EmployeeController employeeController = loader.getController();
//            employeeController.setBasketButtonContent(HotelManagement.selectedRoom.size() + "Item",true);
//        }
        FXMLLoader loader = new FXMLLoader();
        Parent parent = loader.load(getClass().getResource("../EmployeeUI.fxml").openStream());
        EmployeeController employeeController = loader.getController();
        employeeController.setBasketButtonContent(HotelManagement.selectedRoom.size() + "Item",true);
    }

}
