
package mchacks6;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javafx.scene.text.Text;
public class ToolPane extends HBox {
    ImageView ivBullet = new ImageView (new Image("images/bullet.png"));
    ImageView ivGun = new ImageView(new Image("images/gun.png"));
    public ToolPane(){
        setSpacing(7);
        setLayoutY(5);
        setLayoutX(5);
        setPadding(new Insets(0,5,0,5));
        getStyleClass().add("toolPane");
        ivBullet.setFitWidth(10);
        ivBullet.setFitHeight(10);
        setAlignment(Pos.CENTER);
        Text txtNumOfBulletsLeft = new Text("3");
        txtNumOfBulletsLeft.getStyleClass().add("toolPaneText");
        Button btFire = new Button();
        btFire.setId("btFire");
        ivGun.setFitHeight(10);
        ivGun.setFitWidth(10);
        btFire.setGraphic(ivGun);
        btFire.setPrefHeight(10);
        btFire.setPrefWidth(10);
        getChildren().addAll(ivBullet, txtNumOfBulletsLeft, btFire);
        
    }
}
