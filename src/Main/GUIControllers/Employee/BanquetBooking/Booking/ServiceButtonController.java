package Main.GUIControllers.Employee.BanquetBooking.Booking;

import Main.Models.BanquetBookingManagement;
import Main.Models.Navigation;
import Main.Models.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.ParseException;

public class ServiceButtonController {
    public Service service;
    @FXML
    private ImageView dropImage;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView addImage;

    @FXML
    void initialize(){
        addImage.setImage(new Image(getClass().getResourceAsStream("/images/add.png")));
        dropImage.setImage(new Image(getClass().getResourceAsStream("/images/drop.png")));
    }

    @FXML
    void add(ActionEvent event) throws IOException, ParseException {
        BanquetBookingManagement.addSelectedUse(service);
        Navigation.getRestaurantController().updateBasketButton();
        Navigation.getRestaurantController().loadSelectedUses();
    }

    @FXML
    void drop(ActionEvent event) throws IOException, ParseException {
        BanquetBookingManagement.dropSelectedUse(service);
        Navigation.getRestaurantController().updateBasketButton();
        Navigation.getRestaurantController().loadSelectedUses();
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText(nameLabel);
    }

    public void setService(Service service) {
        this.service = service;
    }
}
