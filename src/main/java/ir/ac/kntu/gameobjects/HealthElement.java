package ir.ac.kntu.gameobjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealthElement extends SpecialPowers {

    public HealthElement(double xPos, double yPos) {
        super(xPos, yPos);
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\extrahealth.png"));
        setImageView(imageView);
    }

    public void addBuff(Player player) {
        player.setHealth(player.getHealth() + 1);
        System.out.println("Health:"+player.getHealth());
    }
}
