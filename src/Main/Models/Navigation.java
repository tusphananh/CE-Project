package Main.Models;

import Main.GUIControllers.Employee.BanquetBooking.Booking.RestaurantController;
import Main.GUIControllers.Employee.RoomBooking.CheckIn.CheckInController;
import Main.GUIControllers.Employee.RoomBooking.CheckOut.CheckOutController;
import Main.GUIControllers.Employee.RoomBooking.Booking.BillController;
import Main.GUIControllers.Employee.RoomBooking.Booking.DayPickController;
import Main.GUIControllers.Employee.RoomBooking.Booking.SuccessController;
import Main.GUIControllers.Employee.RoomBooking.Booking.RoomPickController;
import Main.GUIControllers.Manager.ManagerController;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
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
    private static SuccessController informationFormController;
    private static DayPickController dayPickController;
    private static RoomPickController roomPickController;
    private static CheckInController checkInController;
    private static CheckOutController checkOutController;
    private static RestaurantController restaurantController;
    private static ManagerController managerController;
    private static Main.GUIControllers.Employee.BanquetBooking.CheckIn.CheckInController banquetCheckInController;
    public static void navigateNewManager(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/ManagerUI.fxml"));
        Parent parent = fxmlLoader.load();
        managerController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setScene(scene);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth() / 1.5);
        stage.setHeight(bounds.getHeight() / 1.5);
        stage.show();
    }
    public static void navigateNewEmployee(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/RoomBooking/Booking/DatePick.fxml"));
        Parent parent = fxmlLoader.load();
        dayPickController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
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

    public static void logout(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Login.fxml"));
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public static void minimize(javafx.event.ActionEvent actionEvent){
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setIconified(true);
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

    public static SuccessController getInformationFormController() {
        return informationFormController;
    }

    public static DayPickController getDayPickController() {
        return dayPickController;
    }

    public static void setInformationFormController(SuccessController informationFormController) {
        Navigation.informationFormController = informationFormController;
    }

    public static RoomPickController getRoomPickController() {
        return roomPickController;
    }

    public static void setRoomPickController(RoomPickController roomPickController) {
        Navigation.roomPickController = roomPickController;
    }

    public static void setBillController(BillController billController) {
        Navigation.billController = billController;
    }

    public static void setCheckInController(CheckInController checkInController) {
        Navigation.checkInController = checkInController;
    }

    public static void setCheckOutController(CheckOutController checkOutController) {
        Navigation.checkOutController = checkOutController;
    }

    public static void setRestaurantController(RestaurantController restaurantController) {
        Navigation.restaurantController = restaurantController;
    }

    public static CheckInController getCheckInController() {
        return checkInController;
    }

    public static CheckOutController getCheckOutController() {
        return checkOutController;
    }

    public static RestaurantController getRestaurantController() {
        return restaurantController;
    }

    public static void setDayPickController(DayPickController dayPickController) {
        Navigation.dayPickController = dayPickController;
    }

    public static void setBanquetCheckInController(Main.GUIControllers.Employee.BanquetBooking.CheckIn.CheckInController banquetCheckInController) {
        Navigation.banquetCheckInController = banquetCheckInController;
    }

    public static Main.GUIControllers.Employee.BanquetBooking.CheckIn.CheckInController getBanquetCheckInController() {
        return banquetCheckInController;
    }

    public static ManagerController getManagerController() {
        return managerController;
    }
}
