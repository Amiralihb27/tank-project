package ir.ac.kntu.gameObjects;

import javafx.scene.image.ImageView;

public class Tank {
    private int xPoint;

    private int yPoint;

    private int speedX = 5;

    private int speedY = 5;

    private ImageView imageView;

    public Tank(int xPoint, int yPoint, ImageView imageView) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
        this.imageView = imageView;
        this.imageView.setX(xPoint);
        this.imageView.setY(yPoint);
    }

    public int getX() {
        return xPoint;
    }

    public void setX(int xPoint) {
        this.xPoint = xPoint;
    }

    public int getY() {
        return yPoint;
    }

    public void setY(int yPoint) {
        this.yPoint = yPoint;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
