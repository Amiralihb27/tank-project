package ir.ac.kntu.gameobjects;

import ir.ac.kntu.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

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

    private int size = 25;


    public Tank() {

    }

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

    public void addScore(int amount) {
        this.score += amount;
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


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void shootBullet(Pane root) {
        ImageView bullet = new ImageView(new Image(this.getBullet().getBulletImage().getUrl(),
                15, 15, true, true));
        // System.out.println(bullet.getFitHeight());
        root.getChildren().add(bullet);

        // Set initial position of the bullet
        getBullet().setStartingX(this.getXPos()); // Adjust the starting position as needed
        getBullet().setStartingY(this.getYPos());// Adjust the starting position as needed

        getBullet().setSpeedY(-Math.sin(Math.toRadians(getBullet().getAngle())) * getBullet().getBulletSpeed());
        getBullet().setSpeedX(Math.cos(Math.toRadians(getBullet().getAngle())) * getBullet().getBulletSpeed());
        // Set up animation timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.03), event -> updateBullet(bullet)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        // Enable shooting initially
        getBullet().setShooting(true);
        // Set up shooting timeline
        Timeline shootingTimeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            if (!bullet.isVisible()) {
                // Shoot the bullet if it's not visible
                shootBullet(bullet);
            }
        }));
        shootingTimeline.setCycleCount(Timeline.INDEFINITE);
        shootingTimeline.play();
    }

    private void updateBullet(ImageView newBullet) {
        if (newBullet.isVisible()) {
            // Update bullet position
            System.out.println(getBullet().getAngle() + ":Angle");
            System.out.println("X:" + this.getBullet().getSpeedX());
            getBullet().setStartingY(this.getBullet().getSpeedY() + bullet.getStartingY());
            getBullet().setStartingX(this.getBullet().getSpeedX() + bullet.getStartingX());
            // Set bullet position
            newBullet.setLayoutX(getBullet().getStartingX());
            newBullet.setLayoutY(getBullet().getStartingY());
            System.out.println(newBullet.getLayoutX() + " " + newBullet.getLayoutY());

            // Check if bullet is out of bounds
            if (newBullet.getLayoutY() >= 600 || newBullet.getLayoutX() >= 600 ||
                    newBullet.getLayoutY() < 0 || newBullet.getLayoutX() < 0 ||
                     newBullet.getLayoutX()-getBullet().getStartingX()>=100 ||
                    newBullet.getLayoutY()-getBullet().getStartingY()>=100 ) {
                // Respawn the bullet when it reaches the boundary
                System.out.println("Dead");
                respawnBullet(newBullet);
            }
        }
    }

    private void shootBullet(ImageView newBullet) {
        // Reset bullet position
        getBullet().setStartingX(this.getImageView().getX()); // Adjust the starting position as needed
        getBullet().setStartingY(this.getImageView().getY());// Adjust the starting position as needed
        getBullet().setSpeedY(-Math.sin(Math.toRadians(getBullet().getAngle())) * getBullet().getBulletSpeed());
        getBullet().setSpeedX(Math.cos(Math.toRadians(getBullet().getAngle())) * getBullet().getBulletSpeed());
        System.out.println("bullet");

        // Enable shooting
        // getBullet().revive(true);
        newBullet.setVisible(true);
    }

    private void respawnBullet(ImageView newBullet) {
        // Hide the bullet
        getBullet().kill();
        newBullet.setVisible(false);
        getBullet().setSpeedY(-Math.sin(Math.toRadians(getBullet().getAngle())) * getBullet().getBulletSpeed());
        getBullet().setSpeedX(Math.cos(Math.toRadians(getBullet().getAngle())) * getBullet().getBulletSpeed());
        // Disable shooting temporarily
        getBullet().setShooting(false);

        // Reset bullet position after a delay
        Timeline resetTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            getBullet().setStartingX(this.getImageView().getX()); // Adjust the starting position as needed
            getBullet().setStartingY(this.getImageView().getY());// Adjust the starting position as needed

            System.out.println(getBullet().getStartingX() + " starting " + getBullet().getStartingY());
            // Enable shooting again
            getBullet().setShooting(true);
        }));
        resetTimeline.setCycleCount(1);
        resetTimeline.play();
    }
}
