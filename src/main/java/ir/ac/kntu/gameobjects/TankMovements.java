package ir.ac.kntu.gameobjects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Random;

import static ir.ac.kntu.constants.GlobalConstants.canvasHeight;
import static ir.ac.kntu.constants.GlobalConstants.canvasWidth;

public class TankMovements {

    Tank tank;


    public TankMovements(Tank tank) {
        this.tank = tank;
    }

    public void initializeDirection(int size, Collision collision) {
        Random random = new Random();
        int rand = random.nextInt(4);
        if (tank.isFull()) {
            tank.makeEmpty();
            System.out.println("full");
        }
        while (tank.getPositionToRespawn()[rand] == -100) {
            rand = random.nextInt(4);
        }
        tank.setXPos(tank.getPositionToRespawn()[rand]);
        tank.getPositionToRespawn()[rand] = -100;
        tank.getImageView().setX(tank.getXPos());
        tank.setYPos(0);
        tank.getImageView().setY(0);
        chooseToMove(size, collision);
    }

    public void chooseToMove(int size, Collision collision) {
        Random random = new Random();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            int[] directions = {-1, 0, 1};
            tank.setSpeedX(directions[random.nextInt(3)]);
            tank.setSpeedY(directions[random.nextInt(3)]);
            if (tank.getSpeedX() != 0 && tank.getSpeedY() != 0) {
                if (random.nextBoolean()) {
                    tank.setSpeedX(0);
                    tank.chooseVerticalPicture(tank.getSpeedY(), size);
                } else {
                    tank.setSpeedY(0);
                    tank.chooseHorizontalPicture(tank.getSpeedX(), size);
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameLoop(size, collision);
    }


    public void gameLoop(int size, Collision collision) {
        Timeline gameLoop = new Timeline(new KeyFrame(Duration.millis(30), event -> {
            move(size, collision);
        }));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
    }

    public void move(int size, Collision collision) {
        tank.setXPos(tank.getXPos() + tank.getSpeedX());
        tank.setYPos(tank.getYPos() + tank.getSpeedY());
        tank.chooseHorizontalPicture(tank.getSpeedX(), size);
        tank.chooseVerticalPicture(tank.getSpeedY(), size);
        tank.getImageView().setX(tank.getXPos());
        tank.getImageView().setY(tank.getYPos());
        checkingBoundries(size, collision);
    }

    public void checkingBoundries(int size, Collision collision) {
        Bounds bounds = tank.getImageView().getBoundsInParent();
        actAgainstCollision(collision);
        if (bounds.getMinX() < 0) {
            tank.setXPos(0);
            tank.setSpeedX(tank.getSpeedX() * -1);
            tank.getImageView().setX(0);
            tank.chooseHorizontalPicture(tank.getSpeedX(), size);
        }
        if (bounds.getMaxX() > canvasHeight) {
            tank.setXPos(canvasWidth - bounds.getWidth());
            tank.setSpeedX(tank.getSpeedX() * -1);
            tank.getImageView().setX(tank.getXPos());
            tank.chooseHorizontalPicture(tank.getSpeedX(), size);
        }
        if (bounds.getMinY() < 0) {
            tank.setYPos(0);
            tank.getImageView().setY(0);
            tank.setSpeedY(tank.getSpeedY() * -1);
            tank.chooseVerticalPicture(tank.getSpeedY(), size);
            tank.getImageView().setY(0);
        }
        if (bounds.getMaxY() > canvasHeight) {
            tank.setYPos(canvasHeight - bounds.getHeight());
            tank.setSpeedY(tank.getSpeedY() * -1);
            tank.chooseVerticalPicture(tank.getSpeedY(), size);
            tank.getImageView().setY(tank.getYPos());
        }
    }

    public void actAgainstCollision(Collision collision) {
        if (collision.checkCollision(tank, tank.getSpeedX(), tank.getSpeedY())) {
            tank.setSpeedX(tank.getSpeedX() * -1);
            tank.setSpeedY(tank.getSpeedY() * -1);
        }
    }

}
