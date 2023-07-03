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

import java.util.ArrayList;
import java.util.Random;

import static ir.ac.kntu.constants.GlobalConstants.*;

public class OrdinaryTank extends Tank {



    public OrdinaryTank(int speedX, int speedY, ImageView imageView) {
        super(speedX, speedY, imageView);
        super.setHealth(1);
        super.setScore(100);
    }


    public void movement(int size,Collision collision){
        super.movement(size,collision);
    }

    public void chooseHorizontalPicture(int xPos, int size) {
        switch (xPos) {
            case 1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyright.png",
                        size, size, true, true));
                super.getBullet().setAngle(0.0);
                break;
            case -1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyleft.png",
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
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemydown.png",
                        size, size, true, true));
                super.getBullet().setAngle(270.0);
                break;
            case -1:
                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemytank1.png",
                        size, size, true, true));
                super.getBullet().setAngle(90.0);
                break;
            default:
                break;

        }
    }




}



