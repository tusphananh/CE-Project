package Main;

import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    private String password = "123";
    double x,y;
    double opacity = 1;
    @FXML
    private AnchorPane loginPane;

    @FXML
    private Button buttonLogin;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text messagesText;

    @FXML
    public void buttonPressed(javafx.event.ActionEvent actionEvent) throws IOException, InterruptedException {
        System.out.println("Logining");
        if (passwordField.getText().equals(password)){
            messagesText.setText(" Welcome back !");
            messagesText.setFill(Color.web("FED755"));
            System.out.println("Success");

            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    try {
                        Navigation.navigateManager(actionEvent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            new Thread(sleeper).start();

        }
        else {
            System.out.println("Fail");
            messagesText.setText(" Wrong Password !");
            messagesText.setFill(Color.RED);
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent){
        System.out.println("Exit");
        System.exit(-1);
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
    public void enterPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            buttonLogin.fire();
        }
    }

    @FXML
    public void minimize(ActionEvent actionEvent){
        Navigation.minimize(actionEvent);
    }
}
