package ir.ac.kntu.gameobjects;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static ir.ac.kntu.constants.GlobalConstants.*;


public class ShieldTank extends Tank{


    public ShieldTank(int speedX, int speedY, ImageView imageView) {
        super(speedX, speedY, imageView);
        super.setHealth(2);
        super.setScore(200);
    }

    public void movement(int size,Collision collision){
        super.movement(size,collision);
    }

    public void chooseHorizontalPicture(int xPos, int size) {
        if (xPos == 3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyrightmetal.png",
                    size, size, true, true));
            super.getBullet().setAngle(0.0);
        } else if (xPos == -1 * 3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyleftmetal.png",
                    size, size, true, true));
            super.getBullet().setAngle(180.0);
        }
    }

    public void chooseVerticalPicture(int ySpeed, int size) {
//        switch (ySpeed) {
//            case 1:
//                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemydownmetal.png",
//                        size, size, true, true));
//                super.getBullet().setAngle(270.0);
//                break;
//            case -1:
//                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyupmetal.png",
//                        size, size, true, true));
//                super.getBullet().setAngle(90.0);
//                break;
//            default:
//                break;
//
//        }

        if (ySpeed ==3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemydownmetal.png",
                    size, size, true, true));
            super.getBullet().setAngle(270.0);
        } else if (ySpeed == -3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\enemyupmetal.png",
                    size, size, true, true));
            super.getBullet().setAngle(90.0);
        }
    }



}
