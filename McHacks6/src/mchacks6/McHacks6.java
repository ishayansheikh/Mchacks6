package mchacks6;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author chenshandi
 */
public class McHacks6 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Callouts");
        Pane root = new Pane();
        int maxWidth = 1024;

        primaryStage.setScene(new Scene(root, maxWidth, 720));


        Image image = new Image("images/mountain.jpg", true);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());

        Callout callout1 = CalloutBuilder.create(CustomCallout.class)
                .headPoint(600, 550)
                .leaderLineToPoint(400, 300)
                .endLeaderLineLeft()
                .mainTitle("Your Colleague Stabbed")
                .subTitle("Distance from snipper : 20m")
                .pause(50000)
                .build();

        Callout callout2 = CalloutBuilder.create()
                .headPoint(200, 550)
                .leaderLineToPoint(400, 350.5)
                .endLeaderLineRight()
                .mainTitle("Your Colleague Shot")
                .subTitle("Distance from snipper : 300m")
                .pause(50000)
                .build();

        Callout callout3 = CalloutBuilder.create()
                .headPoint(498, 386)
                .leaderLineToPoint(375, 406)
                .endLeaderLineLeft()
                .mainTitle("Your Colleague Shot")
                .subTitle("Distance from snipper : 2000m")
                .pause(50000)
                .build();

        Callout callout4 = CalloutBuilder.create()
                .headPoint(667, 400)
                .leaderLineToPoint(695, 500)
                .endLeaderLineRight()
                .mainTitle("Smoke detected")
                .subTitle("Distance from snipper : 200m")
                .pause(50000)
                .build();

        root.getChildren().add(imageView);
        Label instructions = new Label("Please Kill the snipper");
        
        instructions.setStyle("-fx-text-fill: white; " +
                "-fx-background-color: linear-gradient(#f4ce42, #f4a941); " +
                "-fx-effect: dropshadow(three-pass-box , rgba(0,0,0,0.6), 5, 0.0 , 0 , 1 );" +
                "-fx-border-insets: 10;" +
                "-fx-font-size: 20;" +
                "-fx-label-padding: 5;");
        root.getChildren().add(instructions);
        root.getChildren().addAll(callout1, callout2, callout3, callout4);

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

        // Observe mouse clicked coordinates
        root.setOnMouseClicked(mouseevent -> {
            System.out.println(mouseevent);
        });

        primaryStage.show();
        
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }

}