package ir.ac.kntu.gameobjects;

import javafx.scene.image.ImageView;

public class Tank {
    private double xPos;

    private double yPos;

    private Bullet bullet;

    private int tankSize = 30;

    private int speedY = 5;

    private Direction direction = Direction.UP;

    private int speedX = 5;

    private ImageView imageView;

    private int score;

    private int health;

    private int powerOfTheBullet = 1;

    public Tank(double xPos, double yPos, ImageView imageView) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.imageView = imageView;
    }

    public Tank(int speedX, int speedY, ImageView imageView) {
        this.speedX = speedX;
        this.speedY = speedY;

        this.imageView = imageView;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public int getTankSize() {
        return tankSize;
    }

    public void setTankSize(int tankSize) {
        this.tankSize = tankSize;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealth() {
        return health;
    }

    public void lostHP() {
        if (this.health > 0) {
            this.health--;
        } else {
            this.health = 0;
        }

    }

    public void setHealth(int health) {
        health = health;
    }
}
