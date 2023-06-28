package ir.ac.kntu.gameobjects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.Random;

import static ir.ac.kntu.constants.GlobalConstants.*;

public class OrdinaryTank extends Tank {

    private final int[] positionToRespawn = {0, canvasWidth / 3, 2 * canvasWidth / 3, canvasWidth / 10};


    public OrdinaryTank(int speedX, int speedY, ImageView imageView) {
        super(speedX, speedY, imageView);
        super.setHealth(1);
        super.setScore(100);
    }

    public void initializeDirection(int size,Pane root) {
        Random random = new Random();
        super.setXPos(positionToRespawn[random.nextInt(4)]);
        getImageView().setX(getXPos());
        System.out.println(getXPos());
        super.setYPos(0);
        getImageView().setY(0);
        // create a timeline to change the direction every 5 seconds
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            int[] directions = {-1, 0, 1};
            super.setSpeedX(directions[random.nextInt(3)]);
            super.setSpeedY(directions[random.nextInt(3)]);
            // ensure that the tank can only move in one direction at a time
            if (super.getSpeedX() != 0 && super.getSpeedY() != 0) {
                if (random.nextBoolean()) {
                    super.setSpeedX(0);
                    chooseVerticalPicture(getSpeedY(),size);
                } else {
                    super.setSpeedY(0);
                    chooseHorizontalPicture(getSpeedX(),size);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameLoop(size,root);

        // create a game loop to move the tank
    }

    public void chooseHorizontalPicture(int xPos,int size){
        switch (xPos){
            case 1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyright.png",
                        size, size, true,true));
                break;
            case -1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyleft.png",
                        size, size, true,true));
                break;
            default:
                break;
        }
    }

    public void chooseVerticalPicture(int ySpeed,int size){
        switch (ySpeed){
            case 1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemydown.png",
                        size, size, true,true));
                break;
            case -1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemytank1.png",
                        size, size, true,true));
                break;
            default:
                break;

        }
    }

    public void gameLoop(int size,Pane root) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            move(size,root);
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    public void move(int size,Pane root){
        super.setXPos(super.getXPos() + super.getSpeedX());
        super.setYPos(super.getYPos() + super.getSpeedY());
        chooseHorizontalPicture(getSpeedX(),size);
        chooseVerticalPicture(getSpeedY(),size);
        getImageView().setX(getXPos());
        getImageView().setY(getYPos());
        checkingBoundries(size,root);
    }

    public void checkingBoundries(int size, Pane root){
        Bounds bounds = getImageView().getBoundsInParent();
        if (bounds.getMinX() < 0) {
            super.setXPos(0);
            super.setSpeedX(super.getSpeedX() * -1);
            getImageView().setX(0);
            chooseHorizontalPicture(getSpeedX(),size);
        }
        if (bounds.getMaxX() > canvasHeight) {
            setXPos(canvasWidth - bounds.getWidth());
            setSpeedX(getSpeedX() * -1);
            getImageView().setX(getXPos());
            chooseHorizontalPicture(getSpeedX(),size);
        }
        if (bounds.getMinY() < 0) {
            setYPos(0);
            getImageView().setY(0);
            setSpeedY(getSpeedY() * -1);
            chooseVerticalPicture(getSpeedY(),size);
            getImageView().setY(0);
        }
        if (bounds.getMaxY() > canvasHeight) {
            setYPos(canvasHeight - bounds.getHeight());
            setSpeedY(getSpeedY() * -1);
            chooseVerticalPicture(getSpeedY(),size);
            getImageView().setY(getYPos());
        }
    }


}



