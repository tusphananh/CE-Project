package Main.GUIControllers;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DayPickController {
    private double reloadSpeed = 500;
    static boolean navigateBool = false;
    private VBox checkInPane,checkOutPane,restaurantPane,roomPickerPane;
    private VBox slider;

    @FXML
    private StackPane stackPane,mainStack;
    @FXML
    public DatePicker fromTextField;
    @FXML
    public DatePicker toTextField;

    @FXML
    private Button searchButton;

    @FXML
    private VBox mainPane,bookingPane;

    @FXML
    public Button basketButton;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        datePickerConvert();
        loadCheckIn();
        loadCheckOut();
        loadRestaurant();
        loadSlider();
        stackPane.setOpacity(0);
        Navigation.fadeOut(stackPane,1000);
    }

    @FXML
    public void searchAvailableRoom(ActionEvent actionEvent) throws Exception {
        if (fromTextField.getValue() == null || toTextField.getValue() == null){
            HotelManagement.showAlertInformation("Something goes wrong","Fill start and end date");
        }
        else if (HotelManagement.updateAvailableRooms(fromTextField.getEditor().getText(),toTextField.getEditor().getText())){
             loadRoomPicker();
             showSlideRoomPicker();
        }
    }
    @FXML
    void onMouseClicked(MouseEvent mouseEvent){
        if (navigateBool){
            slideTransition();
        }
    }

    @FXML
    void slide(ActionEvent actionEvent){
        slideTransition();
    }

    void slideTransition(){
        navigateBool = !navigateBool;
        if (navigateBool){
            Navigation.slideHorizontallyTransition(slider,0,300);
        }
        else{
            Navigation.slideHorizontallyTransition(slider,-(slider.getPrefWidth() + 30 ),300);
        }
    }

    private void datePickerConvert(){
        String pattern = "dd-MM-yyyy";

        fromTextField.setPromptText(pattern.toLowerCase());
        toTextField.setPromptText(pattern.toLowerCase());

        fromTextField.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        toTextField.setConverter(new StringConverter<>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }


    void showBooking(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(bookingPane);
    }

    void showCheckIn(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(checkInPane);
    }
    void showCheckOut(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(checkOutPane);
    }
    void showRestaurant(){
        mainStack.getChildren().clear();
        mainStack.getChildren().add(restaurantPane);
    }


    void loadCheckIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/CheckIn.fxml"));
        checkInPane = fxmlLoader.load();
    }
    void loadCheckOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/CheckOut.fxml"));
        checkOutPane = fxmlLoader.load();
    }
    void loadRestaurant() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Restaurant.fxml"));
        restaurantPane = fxmlLoader.load();
    }

    void loadSlider() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/Slider.fxml"));
        slider = fxmlLoader.load();
        stackPane.getChildren().add(slider);
        slider.setTranslateX(-(slider.getPrefWidth() + 30 ));
    }

    void loadRoomPicker() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/RoomPick.fxml"));
        roomPickerPane = fxmlLoader.load();
        roomPickerPane.setVisible(false);
        roomPickerPane.setTranslateX(mainStack.getWidth() + 50);
        Navigation.setRoomPickController(fxmlLoader.getController());
        mainStack.getChildren().add(roomPickerPane);
    }
    void showSlideRoomPicker(){
        roomPickerPane.setVisible(true);
        Navigation.slideHorizontallyTransition(roomPickerPane,0,300);
    }

    public String getFromTextField() {
        return String.valueOf(fromTextField.getValue());
    }

    public String getToTextField() {
        return String.valueOf(toTextField.getValue());
    }

    public StackPane getMainStack() {
        return mainStack;
    }
}
