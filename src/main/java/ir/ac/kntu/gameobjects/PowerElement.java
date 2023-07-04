package ir.ac.kntu.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PowerElement extends SpecialPowers {

    public PowerElement(double xPos, double yPos) {
        super(xPos, yPos);
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\star.png"));
        setImageView(imageView);
    }

    public void addBuff(Player player) {
        player.setPowerOfTheBullet(player.getPowerOfTheBullet() + 1);
        System.out.println("Power Of The Bullet: "+player.getPowerOfTheBullet());
    }

}
