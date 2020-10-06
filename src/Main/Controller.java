package Main;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {
    private String adminPass = "456";
    private String employeePass = "123";
    private SwitchButton switchButton;

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
        if ((passwordField.getText().equals(adminPass) && switchButton.isState()) || (passwordField.getText().equals(employeePass) && !switchButton.isState())  ){
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
                        if (switchButton.isState()){
                            Navigation.navigateManager(actionEvent);
                        }
                        else {
                            Navigation.navigateEmployee(actionEvent);
                        }
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
    public void enterPressed(KeyEvent event){
        if (event.getCode() == KeyCode.ENTER){
            buttonLogin.fire();
        }
    }


    @FXML
    public void initialize() throws IOException {
        switchButton = new SwitchButton();
        switchButton.setLayoutX(buttonLogin.getLayoutX() + 5);
        switchButton.setLayoutY(buttonLogin.getLayoutY() + 50);
        loginPane.getChildren().add(switchButton);
    }

}
