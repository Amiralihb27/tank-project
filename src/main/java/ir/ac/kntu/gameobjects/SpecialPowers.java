package ir.ac.kntu.gameobjects;

import javafx.scene.image.ImageView;

public abstract class SpecialPowers {

    private double xPos;

    private  double yPos;

    private Player player;

    private  ImageView imageView;


    public SpecialPowers(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
        this.imageView.setX(xPos);
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
        this.imageView.setY(yPos);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
        this.imageView.setX(xPos);
        this.imageView.setY(yPos);
    }


    public void addBuff(Player player){

    }
}
