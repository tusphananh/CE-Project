package Main;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Map;

public class RoomPanels extends Pane {
    private String id;
    private long capacity;
    private int price;
    private Image image;
    private Type type;
    private HBox hBox;
    private VBox vBox;
    private ImageView imageView;
    private Text idText,capacityText,priceText,typeText;
    public RoomPanels(String id, long capacity, int price, Type type){
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
    }

    public void initilize() throws IOException {
        hBox = new HBox();
        vBox = new VBox();

        idText = new Text("ID: " + id);
        capacityText = new Text("Capacity: " + capacity);
        priceText = new Text("Price: " + price);
        typeText = new Text("Type: " + type);
        vBox.getChildren().add(idText);
        vBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        vBox.getChildren().add(capacityText);
        vBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        vBox.getChildren().add(priceText);
        vBox.getChildren().add(new Separator(Orientation.HORIZONTAL));
        vBox.getChildren().add(typeText);
        vBox.setSpacing(10.0);

        imageView = new ImageView(image);

        hBox.getChildren().addAll(vBox,imageView);
        hBox.setLayoutX(30);
        hBox.setLayoutY(20);

        this.setMaxWidth(500);
        this.setMinHeight(hBox.getPrefHeight()+ 200 );
        this.setStyle("-fx-border-color: FED755 ; -fx-border-radius: 20 20 20 20;-fx-border-width: 10 ");

        this.getChildren().add(hBox);
    }

    public void setImage(String image) {
    }
}
