package Restaurant;

import javafx.scene.control.Label;

public class FoodItemController {

    public Label nameLabel;
    public Label priceLabel;

    public void setNameLabel(String name) {
        this.nameLabel.setText(name);
    }
    public void setPriceLabel (int price){
        this.priceLabel.setText(String.valueOf(price));
    }
}
