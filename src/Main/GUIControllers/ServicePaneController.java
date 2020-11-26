package Main.GUIControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ServicePaneController {
    @FXML
    private Label nameLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label priceLabel;

    public void setAmountLabel(String amountLabel) {
        this.amountLabel.setText(amountLabel);
    }

    public void setNameLabel(String nameLabel) {
        this.nameLabel.setText(nameLabel);
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel.setText(priceLabel);
    }
}
