package ir.ac.kntu.gameobjects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
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


public class ShieldTank extends Tank{


    public ShieldTank(int speedX, int speedY, ImageView imageView) {
        super(speedX, speedY, imageView);
        super.setHealth(2);
        super.setScore(200);
    }

    public void initializeDirection(int size, Collision collision) {
        Random random = new Random();
        int rand=random.nextInt(4);
        while (getPositionToRespawn()[rand]==-100){
            rand=random.nextInt(4);
        }
        super.setXPos(getPositionToRespawn()[rand]);
        super.setFirstPosX(getPositionToRespawn()[rand]);
        setFirstPosY(0);
        getPositionToRespawn()[rand]=-100;
        getImageView().setX(getXPos());
        super.setYPos(0);
        getImageView().setY(0);
        chooseToMove(size,collision);
    }

    public void chooseToMove(int size, Collision collision){
        Random random=new Random();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            int[] directions = {-1, 0, 1};
            super.setSpeedX(directions[random.nextInt(3)]);
            super.setSpeedY(directions[random.nextInt(3)]);
            if (super.getSpeedX() != 0 && super.getSpeedY() != 0) {
                if (random.nextBoolean()) {
                    super.setSpeedX(0);
                    chooseVerticalPicture(getSpeedY(), size);
                } else {
                    super.setSpeedY(0);
                    chooseHorizontalPicture(getSpeedX(), size);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameLoop(size, collision);
    }

    public void chooseHorizontalPicture(int xPos, int size) {
        switch (xPos) {
            case 1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyrightmetal.png",
                        size, size, true, true));
                super.getBullet().setAngle(0.0);
                break;
            case -1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyleftmetal.png",
                        size, size, true, true));
                super.getBullet().setAngle(180.0);
                break;
            default:
                break;
        }
    }

    public void chooseVerticalPicture(int ySpeed, int size) {
        switch (ySpeed) {
            case 1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemydownmetal.png",
                        size, size, true, true));
                super.getBullet().setAngle(270.0);
                break;
            case -1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyupmetal.png",
                        size, size, true, true));
                super.getBullet().setAngle(90.0);
                break;
            default:
                break;

        }
    }

    public void gameLoop(int size,Collision collision) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            move(size, collision);
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    public void move(int size, Collision collision) {
        super.setXPos(super.getXPos() + super.getSpeedX());
        super.setYPos(super.getYPos() + super.getSpeedY());
        chooseHorizontalPicture(getSpeedX(), size);
        chooseVerticalPicture(getSpeedY(), size);
        getImageView().setX(getXPos());
        getImageView().setY(getYPos());
        checkingBoundries(size, collision);
    }

    public void checkingBoundries(int size, Collision collision) {
        Bounds bounds = getImageView().getBoundsInParent();
        actAgainstCollision(collision);
        if (bounds.getMinX() < 0) {
            super.setXPos(0);
            super.setSpeedX(super.getSpeedX() * -1);
            getImageView().setX(0);
            chooseHorizontalPicture(getSpeedX(), size);
        }
        if (bounds.getMaxX() > canvasHeight) {
            setXPos(canvasWidth - bounds.getWidth());
            setSpeedX(getSpeedX() * -1);
            getImageView().setX(getXPos());
            chooseHorizontalPicture(getSpeedX(), size);
        }
        if (bounds.getMinY() < 0) {
            setYPos(0);
            getImageView().setY(0);
            setSpeedY(getSpeedY() * -1);
            chooseVerticalPicture(getSpeedY(), size);
            getImageView().setY(0);
        }
        if (bounds.getMaxY() > canvasHeight) {
            setYPos(canvasHeight - bounds.getHeight());
            setSpeedY(getSpeedY() * -1);
            chooseVerticalPicture(getSpeedY(), size);
            getImageView().setY(getYPos());
        }
    }

    public void actAgainstCollision(Collision collision) {
        if ( collision.checkCollision(this,getSpeedX(),getSpeedY())) {
            super.setSpeedX(super.getSpeedX() * -1);
            super.setSpeedY(super.getSpeedY() * -1);
        }
    }

    public boolean checkCollision(Tank tank, Group obstaclesGroup) {
        for (Node node : obstaclesGroup.getChildren()) {
            if (tank.getImageView().getBoundsInParent().intersects(node.getBoundsInParent())
                    && !tank.getImageView().equals(node)) {
                // Collision detected, change direction of tank
                // tank.changeDirection();
                return true;
            }
        }
        return false;
    }

}
