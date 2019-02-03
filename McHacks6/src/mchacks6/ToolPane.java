package mchacks6;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static mchacks6.McHacks6.Sniper;
import static mchacks6.McHacks6.crossHair;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static mchacks6.McHacks6.root;

public class ToolPane extends HBox {

    ImageView ivBullet = new ImageView(new Image("images/bullet.png"));
    ImageView ivGun = new ImageView(new Image("images/gun.png"));
    static Text txtNumOfBulletsLeft;

    public ToolPane() {
        setSpacing(7);
        setLayoutY(5);
        setLayoutX(5);
        setPadding(new Insets(0, 5, 0, 5));
        getStyleClass().add("toolPane");
        ivBullet.setFitWidth(10);
        ivBullet.setFitHeight(10);
        setAlignment(Pos.CENTER);
        txtNumOfBulletsLeft = new Text("3");
        txtNumOfBulletsLeft.getStyleClass().add("toolPaneText");
        Button btFire = new Button();
        btFire.setId("btFire");
        ivGun.setFitHeight(10);
        ivGun.setFitWidth(10);
        btFire.setGraphic(ivGun);
        btFire.setPrefHeight(10);
        btFire.setPrefWidth(10);
        getChildren().addAll(ivBullet, txtNumOfBulletsLeft, btFire);
        btFire.setOnMouseClicked(e -> {
            try {
                int numberOfBulletsLeft = Integer.parseInt(txtNumOfBulletsLeft.getText());
                if (numberOfBulletsLeft > 0) {
                    double xStart = crossHair.getLayoutX();
                    double yStart = crossHair.getLayoutY();
                    if ((Sniper.getX() > xStart && xStart > Sniper.getX() - 25)
                            && (Sniper.getY() > yStart && yStart > Sniper.getY() - 25)) {
                        loadGameSound("gunshot.mp3", "dead.wav");
                        //win notification
                        endNotification(true);
                    } else {
                        loadGameSound("gunshot.mp3", "reload.wav");
                        updateBullets();
                    }
                }
            } catch (MalformedURLException ex) {
                Logger.getLogger(ToolPane.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * loads appropriate game sound based whether the sniper is shot
     *
     * @param gameSoundString
     * @param followupSoundString
     * @throws MalformedURLException
     */
    protected static void loadGameSound(String gameSoundString, String followupSoundString) throws MalformedURLException {
        //load newly selected song
        ObservableList<Media> mediaList = FXCollections.observableArrayList();
        File gunFile = new File("C:/Users/chenshandi/Desktop/McHacks6/Mchacks6/McHacks6/src/audio/" + gameSoundString);
        Media gunMedia = new Media(gunFile.toURI().toURL().toExternalForm());
        File followupFile = new File("C:/Users/chenshandi/Desktop/McHacks6/Mchacks6/McHacks6/src/audio/" + followupSoundString);
        Media followupMedia = new Media(followupFile.toURI().toURL().toExternalForm());
        mediaList.addAll(gunMedia, followupMedia);
        playMediaTracks(mediaList);
    }

    private static void playMediaTracks(ObservableList<Media> mediaList) {
        if (mediaList.size() == 0) {
            return;
        }

        MediaPlayer mediaplayer = new MediaPlayer(mediaList.remove(0));
        mediaplayer.play();

        mediaplayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                playMediaTracks(mediaList);
            }
        });
    }

    protected static void updateBullets() {
        int currentNumOfBullets = Integer.parseInt(txtNumOfBulletsLeft.getText());
        if (currentNumOfBullets > 1) {
            currentNumOfBullets--;
        } else {
            endNotification(false);
        }
        txtNumOfBulletsLeft.setText(String.valueOf(currentNumOfBullets));
    }

    protected static void endNotification(boolean won) {
        Platform.runLater(() -> {
            final Stage end = new Stage();
            end.initModality(Modality.APPLICATION_MODAL);
            VBox gameEndResultsPane = new VBox(35);
            Text txtNotification = new Text("");
            txtNotification.getStyleClass().add("txtNotification");
            Button btNotification = new Button();
            if (won) {
                txtNotification.setText("Congrats, You have sniped the sniper!");
                txtNumOfBulletsLeft.setText(String.valueOf(0));
                btNotification.setText("Go to an unsolved map");
                btNotification.setOnMouseClicked(e -> {
                    end.close();
                    reloadMap();
                });
            } else {
                txtNotification.setText(String.valueOf(0));
                txtNotification.setText("You ran out of Bullets, mission failed! ");
                btNotification.setText("Try Again");
                btNotification.setOnMouseClicked(e -> {
                    end.close();
                    txtNumOfBulletsLeft.setText("3");
                });
            }
            gameEndResultsPane.getChildren().addAll(txtNotification, btNotification);
            gameEndResultsPane.setAlignment(Pos.CENTER);
            Scene endScene = new Scene(gameEndResultsPane, 300, 120);
            endScene.getStylesheets().add("css/mchacks6.css");
            end.setScene(endScene);
            end.show();
            end.setOnCloseRequest((WindowEvent we) -> {
                end.close();
            });
        });
    }

    protected static void reloadMap() {
        Sniper = new Point2D(570, 270);
        root.getChildren().clear();
        Image image = new Image("images/lake.jpg", true);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(root.widthProperty());

        Callout callout1 = CalloutBuilder.create(CustomCallout.class)
                .headPoint(630, 550)
                .leaderLineToPoint(700, 600)
                .endLeaderLineLeft()
                .mainTitle("A Bird is killed")
                .subTitle("Distance from snipper : 200m")
                .pause(50000)
                .build();

        Callout callout2 = CalloutBuilder.create()
                .headPoint(300, 200)
                .leaderLineToPoint(300, 300)
                .endLeaderLineRight()
                .mainTitle("Sniper has set Fire here")
                .subTitle("Distance from snipper : close")
                .pause(50000)
                .build();

        Callout callout3 = CalloutBuilder.create()
                .headPoint(667, 270)
                .leaderLineToPoint(580, 360)
                .endLeaderLineRight()
                .mainTitle("Sniper has turn left")
                .subTitle("Distance from snipper : twice closer as where he sets fire"+
                        "/n"+ "close to the lake" )
                .pause(50000)
                .build();

        root.getChildren().add(imageView);
        ToolPane toolPane = new ToolPane();
        root.getChildren().add(toolPane);

        root.getChildren().addAll(callout1, callout2, callout3, crossHair);

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
                default:
                    callout1.play();
            }
        };

        root.addEventHandler(KeyEvent.KEY_TYPED, calloutKeyHandler);
        root.requestFocus();
    }
}
