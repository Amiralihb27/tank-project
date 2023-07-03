package ir.ac.kntu.gameobjects;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import static ir.ac.kntu.constants.GlobalConstants.canvasHeight;
import static ir.ac.kntu.constants.GlobalConstants.canvasWidth;

public class Wall extends GameObjects {

    public Wall(double xPos, double yPos, ImageView imageView) {
        super(xPos, yPos, imageView);
    }

    public void drawWall(Pane root) {
        getImageView().setY(canvasHeight / 15);
        getImageView().setX(canvasWidth / 15);
        root.getChildren().add(getImageView());
        getImageView().setY(getyPos() + canvasHeight);

    }

}
