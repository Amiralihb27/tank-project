package ir.ac.kntu.gameobjects;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.lang.reflect.GenericArrayType;

import static ir.ac.kntu.constants.GlobalConstants.canvasHeight;
import static ir.ac.kntu.constants.GlobalConstants.canvasWidth;


public class Player extends Tank {

    private int playerSize = 30;

    public int getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(int playerSize) {
        this.playerSize = playerSize;
    }

    public Player(double xPos, double yPos, ImageView imageView) {
        super(xPos, yPos, imageView);
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(super.getImageView().getImage(), super.getXPos(), super.getYPos(), playerSize, playerSize);
    }

    public void setImage(Image image) {
        super.getImageView().setImage(image);
    }

    public void move(Scene scene, GraphicsContext gc) {
        Image temp = super.getImageView().getImage();
        draw(gc);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight();
            } else if (event.getCode() == KeyCode.UP) {
                setImage(temp);
                moveUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                moveDown();
            } else if (event.getCode() == KeyCode.SPACE && !super.getBullet().isAlive()) {
                super.getBullet().setShooting(true);
            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                super.getBullet().setShooting(false);
            }
        });
    }


    public void moveUp() {
        super.getBullet().setAngle(90.0);
        super.setDirection(Direction.UP);
        super.getBullet().initializeTheDirection(super.getDirection());
        if (getYPos() + getSpeedY() > 0) {
            super.setYPos(super.getYPos() - super.getSpeedY());
            super.getImageView().setY(super.getYPos());
        }
    }

    public void moveDown() {
        super.getBullet().setAngle(270.0);
        Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank3.png");
        setImage(playerImage2);
        super.setDirection(Direction.DOWN);
        super.getBullet().initializeTheDirection(super.getDirection());
        if (getYPos() + getPlayerSize() < canvasHeight){
            super.setYPos(super.getYPos() + super.getSpeedY());
            super.getImageView().setY(super.getYPos());
        }


    }

    public void moveLeft() {
        super.getBullet().setAngle(180.0);
        Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank2.png");
        setImage(playerImage2);
        super.setDirection(Direction.LEFT);
        super.getBullet().initializeTheDirection(super.getDirection());
        if (getXPos() - getSpeedX() > 0) {
            super.setXPos(super.getXPos() - super.getSpeedX());
            super.getImageView().setX(super.getXPos());
        }
    }

    public void moveRight() {
        super.getBullet().setAngle(0.0);
        Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank4.png");
        setImage(playerImage2);
        super.setDirection(Direction.RIGHT);
        super.getBullet().initializeTheDirection(super.getDirection());
        if (getXPos() + playerSize + getSpeedX() < canvasWidth) {
            super.setXPos(super.getXPos() + super.getSpeedX());
            super.getImageView().setX(super.getXPos());
        }
    }


}
