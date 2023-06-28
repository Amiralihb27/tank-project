package ir.ac.kntu.gameobjects;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class Collision {

    private Group obstaclesGroup;

    public Collision(Group obstaclesGroup){
        this.obstaclesGroup=obstaclesGroup;
    }

    public boolean checkCollision(ImageView tank, double dx, double dy) {
        tank.setLayoutX(tank.getLayoutX() + dx);
        tank.setLayoutY(tank.getLayoutY() + dy);

        for (Node node : obstaclesGroup.getChildren()) {
            if (tank.getBoundsInParent().intersects(node.getBoundsInParent())) {
                return true;
            }
        }
        return false;

    }
}
