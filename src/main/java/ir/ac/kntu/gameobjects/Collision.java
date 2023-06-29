package ir.ac.kntu.gameobjects;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Collision {

    private Group obstaclesGroup;

    public Collision(Group obstaclesGroup) {
        this.obstaclesGroup = obstaclesGroup;
    }

    public boolean checkCollision(ImageView tank, double dx, double dy,Pane root) {
        tank.setLayoutX(tank.getLayoutX() + dx);
        tank.setLayoutY(tank.getLayoutY() + dy);

        for (Node node : obstaclesGroup.getChildren()) {
            if (tank.getBoundsInParent().intersects(node.getBoundsInParent())) {
                return true;
            }
        }

        return false;

    }


    public void destroy(Pane root, ImageView object) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node : obstaclesGroup.getChildren()) {
            if (object.getBoundsInParent().intersects(node.getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    nodes.add(node);
                }

            }
        }
        Node nodesInRoot=new ImageView();
        System.out.println(root.getChildren().size());
        for (Node node : root.getChildren()) {
            if (object.getBoundsInParent().intersects(node.getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    nodesInRoot=node;
                }

            }
        }
        if(root.getChildren().contains(nodesInRoot)){
            root.getChildren().remove(nodesInRoot);
        }

        obstaclesGroup.getChildren().removeAll(nodes);
    }
}
