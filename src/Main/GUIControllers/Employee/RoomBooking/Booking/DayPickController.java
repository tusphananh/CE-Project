package Main.GUIControllers.Employee.RoomBooking.Booking;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DayPickController {
    private double reloadSpeed = 500;
    static boolean navigateBool = false;
    private VBox checkInPane,checkOutPane,restaurantPane,roomPickerPane,successPane,banquetCheckInPane;
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
    public void initialize() throws IOException, InterruptedException {
        datePickerConvert();
        loadSlider();
        stackPane.setOpacity(0);
        Navigation.fadeOut(stackPane,1000);
    }

    @FXML
    public void searchAvailableRoom(ActionEvent actionEvent) throws Exception {
        HotelManagement.setFrom(fromTextField.getEditor().getText());
        HotelManagement.setTo(toTextField.getEditor().getText());
        if (fromTextField.getValue() == null || toTextField.getValue() == null){
            HotelManagement.showAlertInformation("Something goes wrong","Fill start and end date");
        }
        else if (HotelManagement.updateAvailableRooms()){
             loadRoomPicker();
             showSlideRoomPicker();
            System.out.println(HotelManagement.availableRooms
            .size());
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

    public void slideTransition(){
        navigateBool = !navigateBool;
        if (navigateBool){
            Navigation.slideHorizontallyTransition(slider,0,300);
        }
        else{
            Navigation.slideHorizontallyTransition(slider,-(slider.getPrefWidth() + 30 ),300);
        }
    }

    private void datePickerConvert(){
        String pattern = "yyyy-MM-dd";

        fromTextField.setPromptText("From");
        toTextField.setPromptText("To");

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

    public void showBanquetCheckIn() throws IOException {
        loadBanquetCheckIn();
        mainStack.getChildren().clear();
        banquetCheckInPane.setOpacity(0);
        mainStack.getChildren().add(banquetCheckInPane);
        Navigation.fadeOut(banquetCheckInPane,500);
    }


    public void showBooking() throws IOException {
        fromTextField.getEditor().clear();
        toTextField.getEditor().clear();
        loadRoomPicker();
        mainStack.getChildren().clear();
        bookingPane.setOpacity(0);
        mainStack.getChildren().add(bookingPane);
        Navigation.fadeOut(bookingPane,500);
    }

    public void showCheckIn() throws IOException {
        loadCheckIn();
        mainStack.getChildren().clear();
        checkInPane.setOpacity(0);
        mainStack.getChildren().add(checkInPane);
        Navigation.fadeOut(checkInPane,500);
    }
    public void showCheckOut() throws IOException {
        loadCheckOut();
        mainStack.getChildren().clear();
        checkOutPane.setOpacity(0);
        mainStack.getChildren().add(checkOutPane);
        Navigation.fadeOut(checkOutPane,500);
    }
    public void showRestaurant() throws IOException {
        loadRestaurant();
        mainStack.getChildren().clear();
        restaurantPane.setOpacity(0);
        mainStack.getChildren().add(restaurantPane);
        Navigation.fadeOut(restaurantPane,500);
    }

    void loadBanquetCheckIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/CheckIn/CheckIn.fxml"));
        banquetCheckInPane = fxmlLoader.load();
        Navigation.setBanquetCheckInController(fxmlLoader.getController());
    }

    void loadCheckIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/CheckIn/CheckIn.fxml"));
        checkInPane = fxmlLoader.load();
        Navigation.setCheckInController(fxmlLoader.getController());
    }
    void loadCheckOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/CheckOut/CheckOut.fxml"));
        checkOutPane = fxmlLoader.load();
        Navigation.setCheckOutController(fxmlLoader.getController());
    }
    void loadRestaurant() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/Booking/Restaurant.fxml"));
        restaurantPane = fxmlLoader.load();
        Navigation.setRestaurantController(fxmlLoader.getController());
    }

    void loadSlider() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/Slider.fxml"));
        slider = fxmlLoader.load();
        stackPane.getChildren().add(slider);
        slider.setTranslateX(-(slider.getPrefWidth() + 30 ));
    }

    void loadRoomPicker() throws IOException {
        HotelManagement.selectedRoom.clear();
        HotelManagement.selectedUse.clear();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/RoomPick.fxml"));
        roomPickerPane = fxmlLoader.load();
        roomPickerPane.setVisible(false);
        roomPickerPane.setTranslateX(mainStack.getWidth() + 50);
        Navigation.setRoomPickController(fxmlLoader.getController());
        mainStack.getChildren().add(roomPickerPane);
    }
    void showSlideRoomPicker(){
        roomPickerPane.setVisible(true);
        Navigation.slideHorizontallyTransition(roomPickerPane,0,800);
    }

    void hideSlideRoomPicker(){
        roomPickerPane.setDisable(true);
        Navigation.slideHorizontallyTransition(roomPickerPane,roomPickerPane.getWidth(),800);
    }

    void loadRoomBookingSuccess() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/Success.fxml"));
        successPane = fxmlLoader.load();
        successPane.setVisible(false);
        successPane.setOpacity(0);
        mainStack.getChildren().add(successPane);
    }
    void loadBanquetBookingSuccess() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/Booking/Success.fxml"));
        successPane = fxmlLoader.load();
        successPane.setVisible(false);
        successPane.setOpacity(0);
        mainStack.getChildren().add(successPane);
    }
    void showRoomBookingSuccess() throws IOException {
        loadRoomBookingSuccess();
        successPane.setVisible(true);
        Navigation.fadeOut(successPane,500);
    }
    public void showBanquetBookingSuccess() throws IOException {
        loadBanquetBookingSuccess();
        successPane.setVisible(true);
        Navigation.fadeOut(successPane,500);
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
