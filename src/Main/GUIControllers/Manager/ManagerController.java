package Main.GUIControllers.Manager;

import Main.Models.HotelManagement;
import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManagerController {
    private double reloadSpeed = 500;
    static boolean navigateBool = false;
    private VBox slider;

    @FXML
    private StackPane stackPane,mainStack;

    @FXML
    private VBox mainPane,bookingPane;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        loadSlider();
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

}
