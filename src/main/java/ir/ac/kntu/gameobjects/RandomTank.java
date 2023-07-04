package ir.ac.kntu.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

import static ir.ac.kntu.constants.GlobalConstants.tankSize;

public class RandomTank extends Tank {

    private String name;

    private String imageName;

    private SpecialPowers specialPowers;

    public RandomTank(int speedX, int speedY, ImageView imageView) {

        super(speedX, speedY, imageView);
        Random random = new Random();
        if (random.nextBoolean() == true) {
            specialPowers = new HealthElement(0, 0);
        } else {
            specialPowers = new PowerElement(0, 0);
        }
        if (random.nextBoolean() == true) {
            imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemyrandomup.png"));
            name = "OrdinaryTank";
            imageName = "enemyrandom";
            super.setHealth(1);
            super.setScore(100);
        } else {
            imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemyrandommetalup.png"));
            name = "ShieldTank";
            imageName = "enemyrandommetal";
            super.setHealth(2);
            super.setScore(200);
        }
    }

    public SpecialPowers getSpecialPowers() {
        return specialPowers;
    }

    public void setSpecialPowers(SpecialPowers specialPowers) {
        this.specialPowers = specialPowers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void movement(int size, Collision collision) {
        super.movement(size, collision);
    }

    public void chooseHorizontalPicture(int xPos, int size) {
//        switch (xPos) {
//            case 1:
//                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
//                        + "right.png",
//                        size, size, true, true));
//                super.getBullet().setAngle(0.0);
//                break;
//            case -1:
//                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
//                        + "left.png",
//                        size, size, true, true));
//                super.getBullet().setAngle(180.0);
//                break;
//            default:
//                break;
//        }
        if (xPos == 3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
                    + "right.png",
                    size, size, true, true));
            super.getBullet().setAngle(0.0);
        } else if (xPos == -1 * 3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
                    + "left.png",
                    size, size, true, true));
            super.getBullet().setAngle(180.0);
        }
    }

    public void chooseVerticalPicture(int ySpeed, int size) {
//        switch (ySpeed) {
//            case 1:
//                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
//                        + "down.png",
//                        size, size, true, true));
//                super.getBullet().setAngle(270.0);
//                break;
//            case -1:
//                getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
//                        + "up.png",
//                        size, size, true, true));
//                super.getBullet().setAngle(90.0);
//                break;
//            default:
//                break;
//
//        }
        if (ySpeed == 3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
                    + "down.png",
                    size, size, true, true));
            super.getBullet().setAngle(270.0);
        } else if (ySpeed == -3*tankSize/50) {
            getImageView().setImage(new Image("F:\\project4\\src\\main\\resources\\images\\" + imageName
                    + "up.png",
                    size, size, true, true));
            super.getBullet().setAngle(90.0);
        }
    }
}
