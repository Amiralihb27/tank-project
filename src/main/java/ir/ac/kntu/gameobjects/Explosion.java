package ir.ac.kntu.gameobjects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Explosion {

    private ImageView explosion;

    public Explosion(ImageView explosion) {
        this.explosion = explosion;

    }

    public void explosionAnimation(double xPos, double yPos, Pane root) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    // Remove the first image
                    root.getChildren().remove(explosion);
                    // Add the second image after the specified delay
                    explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                            "\\explode1.png", 30, 30, true, true));
                    explosion.setX(xPos);
                    explosion.setY(yPos);
                    root.getChildren().add(explosion);
                }),
                new KeyFrame(Duration.millis(100), e -> {
                    // Remove the second image
                    root.getChildren().remove(explosion);
                    explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                            "\\explode2.png", 30, 30, true, true));
                    explosion.setX(xPos);
                    explosion.setY(yPos);
                    // Add the third image after the specified delay
                    root.getChildren().add(explosion);
                }),
                new KeyFrame(Duration.millis(100 * 2), e -> {
                    // Remove the third image after the specified delay
                    root.getChildren().remove(explosion);
                })
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
}
