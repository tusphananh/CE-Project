package Main.GUIControllers.Manager.Reservation;

import Main.Models.BanquetBookingManagement;
import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import Main.Models.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class EditReservationController {
    private ArrayList<Reservation> reservations = new ArrayList<>();

    @FXML
    private TextField firstIDTextField;

    @FXML
    private TextField lastIDTextFiend;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button searchButton;

    @FXML
    private VBox vStack;

    @FXML
    void initialize(){

    }

    @FXML
    void sortFail(ActionEvent event) throws IOException {
        ArrayList<Reservation> sorted = new ArrayList<>();
        for (Reservation reservation: reservations
             ) {
            if ((reservation.getStatus().equals("fail"))){
                sorted.add(reservation);
            }
        }
        updateStack(sorted);
    }

    @FXML
    void sortPending(ActionEvent event) throws IOException {
        ArrayList<Reservation> sorted = new ArrayList<>();
        for (Reservation reservation: reservations
        ) {
            if ((reservation.getStatus().equals("pending"))){
                sorted.add(reservation);
            }
        }
        updateStack(sorted);
    }

    @FXML
    void sortSuccess(ActionEvent event) throws IOException {
        ArrayList<Reservation> sorted = new ArrayList<>();
        for (Reservation reservation: reservations
        ) {
            if ((reservation.getStatus().equals("success"))){
                sorted.add(reservation);
            }
        }
        updateStack(sorted);
    }

    @FXML
    private void sortUsing(ActionEvent event) throws IOException {
        ArrayList<Reservation> sorted = new ArrayList<>();
        for (Reservation reservation: reservations
        ) {
            if ((reservation.getStatus().equals("using"))){
                sorted.add(reservation);
            }
        }
        updateStack(sorted);
    }

    @FXML
    private void disableIDTextField(KeyEvent event) {
        firstIDTextField.clear();
        lastIDTextFiend.clear();
    }

    @FXML
    private void disablePhoneTextField(KeyEvent event) {
        phoneTextField.clear();
    }

    @FXML
    private void searchByEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            searchButton.fire();
        }
    }

    @FXML
    private void search(ActionEvent actionEvent) throws Exception {
        try{
            if (phoneTextField.getText().isEmpty()){
                loadReservationByID(firstIDTextField.getText(),Integer.parseInt(lastIDTextFiend.getText()));
            }
            else {
                loadReservationByPhone(phoneTextField.getText());
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(primaryStage);
            alert.setTitle("Error");
            alert.setHeaderText("Null Information");
            alert.setContentText("Information not found");
            alert.showAndWait();
        }
        updateStack(reservations);
    }

    private void loadReservationByPhone(String phone) throws Exception {
        reservations.clear();
        reservations.addAll(ManagerManagement.getReservationByPhone(phone));
    }
    private void loadReservationByID(String first,int last) throws Exception {
        reservations.clear();
        reservations.add(ManagerManagement.getReservationByID(first,last));
    }

    protected void updateStack(ArrayList<Reservation> reservations) throws IOException {
        vStack.getChildren().clear();
        for (Reservation r: reservations
             ) {
            FXMLLoader loader = new FXMLLoader(Navigation.class.getResource("../fxml/Manager/Reservation/CheckPane.fxml"));
            Parent parent = loader.load();
            CheckPaneController checkPaneController = loader.getController();
            checkPaneController.setReservation(r);
            checkPaneController.setFirstID(r.getFirst());
            checkPaneController.setLastID(String.valueOf(r.getId()));
            checkPaneController.setName(r.getOwner().getName());
            checkPaneController.setStatus(r.getStatus());
            if (!r.getNote().equals("")){
                checkPaneController.setNoteImage(new Image(getClass().getResourceAsStream("/images/note.png")));
            }
            vStack.getChildren().add(parent);
        }
    }

    protected void updateStack() throws IOException {
        searchButton.fire();
    }
}
