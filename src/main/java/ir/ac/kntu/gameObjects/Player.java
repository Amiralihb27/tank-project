package ir.ac.kntu.gameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Player extends Tank {


    public Player(int x, int y, ImageView imageView) {
        super(x, y, imageView);
    }

    public void move(Scene scene) {

        Image temp = super.getImageView().getImage();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank2.png");
                setImage(playerImage2);
                moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank4.png");
                setImage(playerImage2);
                moveRight();
            } else if (event.getCode() == KeyCode.UP) {
                setImage(temp);
                moveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank3.png");
                setImage(playerImage2);
                moveDown();
            }
        });
    }


    public void setImage(Image image) {
        super.getImageView().setImage(image);
    }

    public void moveUp() {
        super.setY(super.getY() - super.getSpeedY());
        super.getImageView().setY(super.getY());
    }

    public void moveDown() {
        super.setY(super.getY() + super.getSpeedY());
        super.getImageView().setY(super.getY());
    }

    public void moveLeft() {
        super.setX(super.getX() - super.getSpeedX());
        super.getImageView().setX(super.getX());
    }

    public void moveRight() {
        super.setX(super.getX() + super.getSpeedX());
        super.getImageView().setX(super.getX());
    }


}

