package ir.ac.kntu.gameobjects;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class GameObjects {

    private double xPos;

    private double yPos;

    private int health=1;

    private int score;

    private ImageView imageView;


    public GameObjects(double xPos,double yPos,ImageView imageView){
        this.xPos=xPos;
        this.yPos=yPos;
        this.imageView=imageView;
        getImageView().setX(xPos);
        getImageView().setY(yPos);
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(health>0){
            this.health = health;
        }else {
            this.health=0;
        }

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void lostHP(int power){
        setHealth(getHealth()-power);
    }
}
