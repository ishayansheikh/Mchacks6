package mchacks6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author chenshandi
 */
public class McHacks6 extends Application {

    static Point2D Sniper = new Point2D(674, 400);
    static CrossHair crossHair = new CrossHair();
    static Pane root;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snipe the Sniper");
        root = new Pane();
        int maxWidth = 1024;
        Scene scene = new Scene(root, maxWidth, 720);
        scene.getStylesheets().add("css/mchacks6.css");
        primaryStage.setMaxWidth(maxWidth);
        primaryStage.setMinWidth(maxWidth);
        primaryStage.setScene(scene);
        Image image = new Image("images/mountain.jpg", true);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());

        Callout callout1 = CalloutBuilder.create(CustomCallout.class)
                .headPoint(600, 550)
                .leaderLineToPoint(700, 600)
                .endLeaderLineLeft()
                .mainTitle("Your Colleague Shot")
                .subTitle("Distance from snipper : 2000m")
                .pause(60000)
                .build();

        Callout callout2 = CalloutBuilder.create()
                .headPoint(200, 550)
                .leaderLineToPoint(100, 600)
                .endLeaderLineRight()
                .mainTitle("Your Colleague Shot")
                .subTitle("Distance from snipper : 3000m")
                .pause(60000)
                .build();

        Callout callout3 = CalloutBuilder.create()
                .headPoint(498, 386)
                .leaderLineToPoint(598, 460)
                .endLeaderLineLeft()
                .mainTitle("Your Colleague Shot")
                .subTitle("Distance from snipper : 10000m")
                .pause(60000)
                .build();

        Callout callout4 = CalloutBuilder.create()
                .headPoint(667, 400)
                .leaderLineToPoint(695, 500)
                .endLeaderLineRight()
                .mainTitle("Your Colleague Stabbed")
                .subTitle("Distance from snipper : 20m")
                .pause(60000)
                .build();

        root.getChildren().add(imageView);
        ToolPane toolPane = new ToolPane();
        root.getChildren().add(toolPane);

        root.getChildren().addAll(callout1, callout2, callout3, callout4, crossHair);

        // Animate 1-4 callouts to point out things in the picture
        EventHandler<KeyEvent> calloutKeyHandler = keyEvent -> {
            switch (keyEvent.getCharacter()) {
                case "1":
                    callout1.play();
                    break;
                case "2":
                    callout2.play();
                    break;
                case "3":
                    callout3.play();
                    break;
                case "4":
                    callout4.play();
                    break;
                default:
                    callout1.play();
            }
        };
        root.addEventHandler(KeyEvent.KEY_TYPED, calloutKeyHandler);
        root.requestFocus();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
