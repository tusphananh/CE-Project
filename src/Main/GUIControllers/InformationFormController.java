package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
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
    private double fadeSpeed = 300;
    private double slideSpeed = 500;
    private boolean navigateBool = false;

    @FXML
    private AnchorPane mainPane;

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
    private TextField phoneTextField,nameTextField,idTextField;

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
            Navigation.fadeIn(secondPane,fadeSpeed);
            Navigation.fadeOut(firstPane,fadeSpeed);

            translateTransition.setToX(0);
            translateTransition.play();
            navigationPane.setTranslateX(0);
            firstCircle.setFill(Paint.valueOf("D7D7D7"));
            secondCircle.setFill(Paint.valueOf("F2F2F2"));
        }
        else {
            firstPane.setOpacity(0);
            Navigation.fadeIn(firstPane,fadeSpeed);
            Navigation.fadeOut(secondPane,fadeSpeed);
            translateTransition.setToX(-451);
            translateTransition.play();
            navigationPane.setTranslateX(-451);
            secondCircle.setFill(Paint.valueOf("D7D7D7"));
            firstCircle.setFill(Paint.valueOf("F2F2F2"));
        }

    }


    public static void setImageView(Image image) {
        InformationFormController.image = image;
    }

    @FXML
    private void confirm(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent parent = fxmlLoader.load(getClass().getResource("../fxml/Bill.fxml").openStream());
        BillController billController = fxmlLoader.getController();
        billController.setPhoneText(getCustomerPhone());
        billController.setNameText(getCustomerName());
        billController.setIdText(getCustomerID());

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(fadeSpeed));
        fadeTransition.setNode(mainPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                stage.close();
            }
        });
        fadeTransition.play();
        Navigation.showBill(actionEvent,parent);
    }

    public String getCustomerPhone(){
        return phoneTextField.getText();
    }

    public String getCustomerName(){
        return nameTextField.getText();
    }

    public String getCustomerID(){
        return idTextField.getText();
    }
}
