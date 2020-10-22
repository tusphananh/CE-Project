package Main;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

public class InformationFormController {
    private double x,y;
    private static Image image;
    private double fadeSpeed = 300;
    private double slideSpeed = 500;
    private boolean navigateBool = false;
    @FXML
    private Pane firstPane,secondPane;

    @FXML
    private Circle firstCircle,secondCircle;

    @FXML
    private FlowPane flowPane;

    @FXML
    private HBox navigationPane;

    @FXML
    private Button nextButton;

    @FXML
    private TextField phoneTextField,nameTextField,idTextField,noteTextField;

    @FXML
    private Circle circle;

    @FXML
    public void initialize(){
        circle.setFill(new ImagePattern(image));
        firstCircle.setFill(Paint.valueOf("D7D7D7"));
        secondCircle.setFill(Paint.valueOf("F2F2F2"));
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

    @FXML
    public void nextSlide(ActionEvent actionEvent){
        Node node = navigationPane;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(slideSpeed), node);
        navigateBool = !navigateBool;
        if (!navigateBool){
            secondPane.setOpacity(0);
            fadeIn(secondPane);
            fadeOut(firstPane);

            translateTransition.setToX(0);
            translateTransition.play();
            navigationPane.setTranslateX(0);
            firstCircle.setFill(Paint.valueOf("D7D7D7"));
            secondCircle.setFill(Paint.valueOf("F2F2F2"));
        }
        else {
            firstPane.setOpacity(0);
            fadeIn(firstPane);
            fadeOut(secondPane);
            translateTransition.setToX(-451);
            translateTransition.play();
            navigationPane.setTranslateX(-451);
            secondCircle.setFill(Paint.valueOf("D7D7D7"));
            firstCircle.setFill(Paint.valueOf("F2F2F2"));
        }

    }
    private void fadeOut(Node node){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(fadeSpeed));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }
    private void fadeIn(Node node){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(fadeSpeed));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

    public static void setImageView(Image image) {
        InformationFormController.image = image;
    }
}
