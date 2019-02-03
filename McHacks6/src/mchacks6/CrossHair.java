package mchacks6;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class CrossHair extends Button {

    ImageView ivCrossHair = new ImageView(new Image("images/crosshair.png"));

    public CrossHair() {
        ivCrossHair.setFitWidth(25);
        ivCrossHair.setFitHeight(25);
        setLayoutX(300);
        setLayoutY(300);
        getStyleClass().add("crossHair");
        setGraphic(ivCrossHair);
        /*
        setOnMouseDragged(event -> {
            setManaged(false);
            this.setTranslateX(event.getX() + this.getTranslateX());
            this.setTranslateY(event.getY() + this.getTranslateY());
            event.consume();
        });
         */
        

    }
}
