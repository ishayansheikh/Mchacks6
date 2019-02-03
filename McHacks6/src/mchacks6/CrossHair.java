package mchacks6;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CrossHair extends Button {

    ImageView ivCrossHair = new ImageView(new Image("images/crosshair.png"));
    final Delta dragDelta = new Delta();

    public CrossHair() {
        ivCrossHair.setFitWidth(25);
        ivCrossHair.setFitHeight(25);
        setLayoutX(300);
        setLayoutY(300);
        getStyleClass().add("crossHair");
        setGraphic(ivCrossHair);
        setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = getLayoutY() - mouseEvent.getSceneY();
            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            }
        });
    }
}

class Delta {

    double x, y;
}
