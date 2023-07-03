package ir.ac.kntu.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;


public class Bullet {
    private double xPos;

    private double yPos;

    private double speedX;

    private double speedY;

    private boolean alive = false;

    private double angle = 90.0;

    private int bulletSize = 15;

    private double startingX;

    private double startingY;

    private boolean shooting = false;

    private Image bulletImage;

    private double bulletSpeed = 5.0;

    public Bullet(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.speedX = 0.0;
        this.speedY = 0.0;
        this.alive = false;
        bulletImage = new Image("F:\\project4\\src\\main\\resources\\images\\UpBullet.png");
    }

    public double getBulletSpeed() {
        return bulletSpeed;
    }


    public void setBulletSpeed(double bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }


    public Image getBulletImage() {
        return bulletImage;
    }

    public void setBulletImage(Image bulletImage) {
        this.bulletImage = bulletImage;
    }

    public double getStartingX() {
        return startingX;
    }

    public void setStartingX(double startingX) {
        this.startingX = startingX;
    }

    public double getStartingY() {
        return startingY;
    }

    public void setStartingY(double startingY) {
        this.startingY = startingY;
    }

    public int getBulletSize() {
        return bulletSize;
    }


    public void setBulletSize(int bulletSize) {
        this.bulletSize = bulletSize;
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
        alive = true;
    }


    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = speedX;
        alive = true;
    }


    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public void revive(boolean status){
        alive=status;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public void update(double deltaTime) {
        xPos += speedX * deltaTime;
        yPos += speedY * deltaTime;
    }

    public void initializeTheDirection(Direction direction) {
        switch (direction) {
            case UP:
                bulletImage = new Image("F:\\project4\\src\\main\\resources\\images\\UpBullet.png");
            case DOWN:
                bulletImage = new Image("F:\\project4\\src\\main\\resources\\images\\UpBullet.png");
            case LEFT:
                bulletImage = new Image("F:\\project4\\src\\main\\resources\\images\\UpBullet.png");
            case RIGHT:
                bulletImage = new Image("F:\\project4\\src\\main\\resources\\images\\RightBullet.png");
            default:
                bulletImage = new Image("F:\\project4\\src\\main\\resources\\images\\UpBullet.png");
        }
    }

    public void draw(GraphicsContext gc) {
        if (alive) {
            gc.drawImage(bulletImage, xPos, yPos, bulletSize, bulletSize);

        }
    }
}

