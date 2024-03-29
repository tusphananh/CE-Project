package Main.GUIControllers.Employee.BanquetBooking.Booking;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestaurantController {
    @FXML
    private VBox bookingPane;

    @FXML
    private DatePicker fromTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField noteTextField;

    @FXML
    private TextField hourTextField;

    @FXML
    private ScrollPane listOfPane;

    @FXML
    private javafx.scene.layout.FlowPane serviceStack;

    @FXML
    private VBox selectedStack;

    @FXML
    private Button basketButton;

    @FXML
    void initialize() throws IOException {
        datePickerConvert();
        basketButton.setOpacity(0);
        loadService();
    }

    @FXML
    void enter(KeyEvent keyEvent) throws SQLException {
        if(keyEvent.getCode() == KeyCode.ENTER){
            Owner owner = RoomBookingManagement.findCustomerByPhone(phoneTextField.getText());
            if ( owner != null){
                nameTextField.setText(owner.getName());
            }
        }
    }

    void loadService() throws IOException {
        for (Service service: BanquetBookingManagement.getServices()
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/Booking/ServiceButton.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServiceButtonController serviceButtonController = fxmlLoader.getController();
            serviceButtonController.setService(service);
            serviceButtonController.setNameLabel(service.getName());
            serviceStack.getChildren().add(servicePane);
        }
    }
    void loadSelectedUses() throws IOException {
        selectedStack.getChildren().clear();
        for (Use use: BanquetBookingManagement.selectedUse
        ) {
            FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource("../fxml/Employee/BanquetBooking/Booking/ServicePane.fxml"));
            Parent servicePane = fxmlLoader.load();
            ServicePaneController servicePaneController = fxmlLoader.getController();
            servicePaneController.setPriceLabel(RoomBookingManagement.moneyFormat(String.valueOf(use.getTotalPrice())));
            servicePaneController.setNameLabel(use.getService().getName());
            servicePaneController.setAmountLabel(String.valueOf(use.getAmount()));
            selectedStack.getChildren().add(servicePane);
        }
    }

    public void updateBasketButton(){
        if (!BanquetBookingManagement.selectedUse.isEmpty()){
            basketButton.setDisable(false);
            basketButton.setText(BanquetBookingManagement.selectedUse.size() + " Item");
            Navigation.fadeOut(basketButton,300);
        }
        else {
            basketButton.setDisable(true);
            basketButton.setText("Basket");
            Navigation.fadeIn(basketButton,300);
        }
    }


    private void datePickerConvert(){
        String pattern = "yyyy-MM-dd";

        fromTextField.setPromptText("From");

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
    }

    @FXML
    void showBill(ActionEvent actionEvent) throws Exception {
        if (nameTextField.getText().isEmpty() || phoneTextField.getText().isEmpty()){
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(primaryStage);
            alert.setTitle("Information");
            alert.setHeaderText("Fill all customer information");
            alert.show();
        }
        else {
            BanquetBookingManagement.setNote(noteTextField.getText());
            BanquetBookingManagement.setFrom(fromTextField.getEditor().getText());
            BanquetBookingManagement.setHour(hourTextField.getText());
            BanquetBookingManagement.setOwner(new Owner(nameTextField.getText(),phoneTextField.getText()));

            BanquetBookingManagement.addOwner();
            BanquetBookingManagement.addReservation();
            Navigation.getDayPickController().showBanquetBookingSuccess();
        }
    }

}
