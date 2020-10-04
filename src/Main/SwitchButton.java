package Main;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

public class SwitchButton extends StackPane {
    private final Rectangle back = new Rectangle(30, 10, Color.RED);
    private final Button button = new Button();
    private String buttonStyleOff = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 0.1, 0.0, 0.0, 1); -fx-background-color: WHITE; -fx-border-color: #FED755 ";
    private String buttonStyleOn = "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 0.1, 0.0, 0.0, 1); -fx-background-color: WHITE; -fx-border-color: #FED755";
    private Text text = new Text("Employee");
    private VBox vBox = new VBox();

    public boolean isState() {
        return state;
    }

    private boolean state = false;

    private void init() {
        setMinSize(30, 15);
        back.maxWidth(30);
        back.minWidth(30);
        back.maxHeight(10);
        back.minHeight(10);
        back.setArcHeight(back.getHeight());
        back.setArcWidth(back.getHeight());
        back.setFill(Color.valueOf("#FED755"));
        Double r = 2.0;
        button.setShape(new Circle(r));
        setAlignment(button, Pos.CENTER_LEFT);
        button.setMaxSize(15, 15);
        button.setMinSize(15, 15);
        button.setStyle(buttonStyleOff);
        text.setFill(Color.valueOf("#FED755"));
        text.setTranslateX(back.getX());
        text.setTranslateY((back.getY() + 30));
        getChildren().addAll(back, button,text);
    }

    public SwitchButton() throws IOException {
        init();
        back.setFill(Color.valueOf("#FED755"));
        EventHandler<Event> click = new EventHandler<Event>() {
            @Override
            public void handle(Event e) {
                if (state) {
                    button.setStyle(buttonStyleOff);
                    setAlignment(button, Pos.CENTER_LEFT);
                    state = false;
                    text.setText("Employee");
                } else {
                    button.setStyle(buttonStyleOn);
                    setAlignment(button, Pos.CENTER_RIGHT);
                    state = true;
                    text.setText("Admin");
                }
            }
        };

        button.setFocusTraversable(false);
        setOnMouseClicked(click);
        button.setOnMouseClicked(click);
    }

}
