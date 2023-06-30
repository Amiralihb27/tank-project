package ir.ac.kntu.gameobjects;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class MetalWall extends Wall {

    public MetalWall(double xPos, double yPos, ImageView imageView) {
        super(xPos, yPos, imageView);
        setHealth(2);
    }

    public void drawWall(Pane root) {
//        getImageView().setX(canvasHeight / 15);
//        getImageView().setY(canvasWidth / 15);

        //root.getChildren().add(getImageView());

        root.getChildren().addAll(getImageView());
//            setImageView(new ImageView(getImageView().getImage()));
//
//            getImageView().setY(getImageView().getY() + canvasHeight / 15);
        //root.getChildren().add(getImageView());
    }
}
