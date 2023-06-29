package ir.ac.kntu.gameobjects;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Collision {

    private Group obstaclesGroup;

    private ArrayList<Wall> walls = new ArrayList<>();

    public Collision(Group obstaclesGroup) {
        this.obstaclesGroup = obstaclesGroup;
        this.walls = walls;
    }

    public Collision(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public Group getObstaclesGroup() {
        return obstaclesGroup;
    }

    public void setObstaclesGroup(Group obstaclesGroup) {
        this.obstaclesGroup = obstaclesGroup;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public boolean checkCollision(ImageView tank, double dx, double dy, Pane root) {
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

        for (Wall wall : walls) {
            if (object.getBoundsInParent().intersects(wall.getImageView().getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    System.out.println("haha");
                    if (root.getChildren().contains(wall.getImageView())) {
                        root.getChildren().remove(wall.getImageView());
                    }
                }

            }
        }


        obstaclesGroup.getChildren().removeAll(nodes);
    }

    public boolean checkCollision2(ImageView object, double dx, double dy, Pane root) {
//        object.setLayoutX(object.getLayoutX() + dx);
//        object.setLayoutY(object.getLayoutY() + dy);
        ImageView imageView = new ImageView(new Image(object.getImage().getUrl()));
        imageView.setY(object.getLayoutY());
        imageView.setX(object.getLayoutX());

        for (Wall wall : walls) {
            if (object.getBoundsInParent().intersects(wall.getImageView().getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    if (root.getChildren().contains(wall.getImageView())) {
                        root.getChildren().remove(wall.getImageView());
                        return true;
                    }
                }

            }
        }

        return false;
    }
}
