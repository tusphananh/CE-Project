package Main.GUIControllers.Manager;

import Main.GUIControllers.Manager.Employee.EditEmployeeController;
import Main.GUIControllers.Manager.Owner.EditOwnerController;
import Main.GUIControllers.Manager.Reservation.EditReservationController;
import Main.GUIControllers.Manager.Room.EditRoomController;
import Main.GUIControllers.Manager.Service.EditServiceController;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ManagerController {
    static boolean navigateBool = false;
    private VBox slider;
    private MainController mainController;
    private EditServiceController editServiceController;
    private EditRoomController editRoomController;
    private EditEmployeeController editEmployeeController;
    private EditReservationController editReservationController;
    private EditOwnerController editOwnerController;

    @FXML
    private StackPane stackPane,mainStack;

    @FXML
    private VBox mainPane;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        loadSlider();
        showMain();
        stackPane.setOpacity(0);
        Navigation.fadeOut(stackPane,1000);
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
    void loadSlider() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Slider.fxml"));
        slider = fxmlLoader.load();
        stackPane.getChildren().add(slider);
        slider.setTranslateX(-(slider.getPrefWidth() + 30 ));
    }

    @FXML
    void onMouseClicked(MouseEvent mouseEvent){
        if (navigateBool){
            slideTransition();
        }
    }

    protected void showMain() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Main.fxml"));
        Parent parent = fxmlLoader.load();
        mainController = fxmlLoader.getController();
        mainStack.getChildren().clear();
        mainStack.getChildren().add(parent);
    }
    protected void showEditRoom() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Room/EditRoom.fxml"));
        Parent parent = fxmlLoader.load();
        editRoomController = fxmlLoader.getController();
        mainStack.getChildren().clear();
        mainStack.getChildren().add(parent);
    }
    protected void showEditEmployee() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Employee/EditEmployee.fxml"));
        Parent parent = fxmlLoader.load();
        editEmployeeController = fxmlLoader.getController();
        mainStack.getChildren().clear();
        mainStack.getChildren().add(parent);
    }
    protected void showEditReservation() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Reservation/EditReservation.fxml"));
        Parent parent = fxmlLoader.load();
        editReservationController = fxmlLoader.getController();
        mainStack.getChildren().clear();
        mainStack.getChildren().add(parent);
    }

    protected void showEditService() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Service/EditService.fxml"));
        Parent parent = fxmlLoader.load();
        editServiceController = fxmlLoader.getController();
        mainStack.getChildren().clear();
        mainStack.getChildren().add(parent);
    }

    protected void showEditOwner() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Owner/EditCustomer.fxml"));
        Parent parent = fxmlLoader.load();
        editOwnerController = fxmlLoader.getController();
        mainStack.getChildren().clear();
        mainStack.getChildren().add(parent);
    }

    public MainController getMainController() {
        return mainController;
    }

    public EditOwnerController getEditOwnerController() {
        return editOwnerController;
    }

    public EditServiceController getEditServiceController() {
        return editServiceController;
    }

    public EditRoomController getEditRoomController() {
        return editRoomController;
    }

    public EditEmployeeController getEditEmployeeController() {
        return editEmployeeController;
    }

    public EditReservationController getEditReservationController() {
        return editReservationController;
    }
}
