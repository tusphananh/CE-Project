package Main;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.EventListener;
import java.util.Map;

public class RoomPanels extends Pane {
    private String id;
    private long capacity;
    private double price;
    private Type type;
    private HBox hBox;
    private VBox vBox;
    private ImageView imageView;
    private Text idText,capacityText,priceText,typeText;
    private Button button;
    private Image image;
    public RoomPanels(String id, long capacity, int price, Type type){
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
    }
    public RoomPanels(String id, long capacity, double price, Type type,String image){
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        setImage(image);
    }

    public void initilize() throws IOException {
        hBox = new HBox();
        vBox = new VBox();

        idText = new Text("ID: " + id);
        capacityText = new Text("Capacity: " + capacity);
        priceText = new Text("Price: " + price + "$");
        typeText = new Text("Type: " + type.getValue());
        button = new Button("Reserve");

        button.setTextFill(Paint.valueOf("WHITE"));
        button.setStyle("-fx-background-color:FED755; -fx-background-radius: 10 10 10 10 ; ");
        button.setEffect(new DropShadow(5.0, Color.valueOf("E9E9E9")));
        button.setMinWidth(vBox.getWidth() + 100);
        button.setTranslateY(30);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Navigation.showInformationForm(mouseEvent);
                System.out.println(id);
            }
        });

        vBox.getChildren().add(idText);
        vBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        vBox.getChildren().add(capacityText);
        vBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        vBox.getChildren().add(priceText);
        vBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        vBox.getChildren().add(typeText);
        vBox.getChildren().add(button);
        vBox.setSpacing(10.0);

        hBox.getChildren().addAll(vBox,imageView);
        hBox.setLayoutX(45);
        hBox.setLayoutY(25);
        hBox.setSpacing(80);
        hBox.setMaxWidth(450);

        this.setMaxWidth(450);
        this.setMinHeight(vBox.getHeight() + 250 );
        this.getChildren().add(hBox);
        this.setStyle("-fx-border-color: FED755 ; " +
                "-fx-border-radius: 20 20 20 20;" +
                "-fx-border-width: 10;"
                );
    }

    public void setImage(String image) {
        this.image = new Image(getClass().getResourceAsStream("/images/" + image));
        imageView = new ImageView(this.image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        imageView.setEffect(new DropShadow(10, Color.valueOf("D0D0D0")));
    }

}
