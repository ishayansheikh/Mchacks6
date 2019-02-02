package mchacks6;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author chenshandi
 */
public class McHacks6 extends Application {

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color:#685343");
        Image imgBackground = new Image("images/mountain.jpg");
        ImageView ivBackground = new ImageView(imgBackground);
        root.getChildren().add(ivBackground);
        ivBackground.fitHeightProperty().bind(root.heightProperty());
        ivBackground.fitWidthProperty().bind(root.widthProperty());
        SequentialTransition calloutAnimation = new SequentialTransition();
        calloutAnimation.setCycleCount(2);
        calloutAnimation.setAutoReverse(true);
        Circle head = new Circle(600, 550, 5); // center x, y, radius
        head.setFill(Color.WHITE);
        calloutAnimation.getChildren().add(buildHeadAnim(head));
        calloutAnimation.play();
        Scene scene = new Scene(root, 800, 650);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    protected Animation buildHeadAnim(Node head) {
        Circle headCircle = (Circle) head;
        return new Timeline(
                new KeyFrame(Duration.millis(1),
                        new KeyValue(headCircle.visibleProperty(), true),
                        new KeyValue(headCircle.radiusProperty(), 0)), // start value
                new KeyFrame(Duration.millis(300),
                        new KeyValue(headCircle.radiusProperty(), 5.0d)) // end value
        );
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
