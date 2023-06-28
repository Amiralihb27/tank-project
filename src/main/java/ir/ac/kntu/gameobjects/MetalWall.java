package ir.ac.kntu.gameobjects;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class MetalWall extends Wall{

    public MetalWall(double xPos, double yPos, ImageView imageView){
        super(xPos, yPos, imageView);
        setHealth(2);
    }
}
