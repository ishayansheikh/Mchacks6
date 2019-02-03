package mchacks6;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static mchacks6.McHacks6.Sniper;
import static mchacks6.McHacks6.crossHair;

import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
                btNotification.setText("Go to an unsolved map");
            }
            else{
                txtNotification.setText("You ran out of Bullets, mission failed! ");
                btNotification.setText("Try Again");
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
}
