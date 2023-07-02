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


    private ArrayList<Tank> tanks = new ArrayList<>();

    private ImageView explosion;

    public Collision(Group obstaclesGroup) {
        this.obstaclesGroup = obstaclesGroup;
        this.walls = walls;
    }

    public Collision(ArrayList<Wall> walls) {
        this.walls = walls;
    }


    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
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

//    public boolean checkCollision(ImageView tank, double dx, double dy) {
//        tank.setLayoutX(tank.getLayoutX() + dx);
//        tank.setLayoutY(tank.getLayoutY() + dy);
//
//        for (Node node : obstaclesGroup.getChildren()) {
//            if (tank.getBoundsInParent().intersects(node.getBoundsInParent())) {
//                return true;
//            }
//        }
//
//        return false;
//
//    }

    public boolean checkCollision(Tank tank, double dx, double dy) {

        ImageView copy;
        if (tank.getClass().getSimpleName().equals("Player")) {
            copy = makeCopy(tank);
            copy.setLayoutX(tank.getImageView().getLayoutX() + dx);
            copy.setLayoutY(tank.getImageView().getLayoutY() + dy);
        } else {
            copy = tank.getImageView();
        }


        for (Wall wall : this.walls) {
            if (copy.getBoundsInParent().intersects(wall.getImageView().getBoundsInParent())) {
                return true;
            }
        }

        for (Tank other : this.tanks) {
            if (copy.getBoundsInParent().intersects(other.getImageView().getBoundsInParent())
                    && !tank.equals(other)) {
                return true;
            }
        }
        return false;

    }

    public ImageView makeCopy(Tank tank) {
        ImageView copy = new ImageView();
        double angle = tank.getBullet().getAngle();
        int dx = 0;
        int dy = 0;
        if (angle == 0.0) {
            dx += tank.getTankSize() / 2;
            dy += tank.getTankSize() / 2;
        } else if (angle == 270.0) {
            dy += tank.getTankSize() / 2;
            dx += tank.getTankSize() / 2;
        } else if (angle == 180.0) {
            dy += tank.getTankSize() / 2;
        }
        copy.setX(tank.getImageView().getX() + dx);
        copy.setY(tank.getImageView().getY() + dy);
        copy.setImage(tank.getImageView().getImage());
        return copy;
    }


    public void destroy(Pane root, ImageView object) {
        ArrayList<Node> nodes = new ArrayList<>();
        for (Node node : obstaclesGroup.getChildren()) {
            if (object.getBoundsInParent().intersects(node.getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    nodes.add(node);
                    break;
                }

            }
        }

        for (Wall wall : walls) {
            if (object.getBoundsInParent().intersects(wall.getImageView().getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    if (root.getChildren().contains(wall.getImageView())) {
                        root.getChildren().remove(wall.getImageView());
                        break;
                    }
                }

            }
        }

        obstaclesGroup.getChildren().removeAll(nodes);
    }

    public boolean destroyWalls(ImageView object, double dx, double dy, Pane root) {
        object.setLayoutX(object.getLayoutX() + dx);
        object.setLayoutY(object.getLayoutY() + dy);
//        ImageView imageView = new ImageView(new Image(object.getImage().getUrl()));
//        imageView.setY(object.getLayoutY());
//        imageView.setX(object.getLayoutX());

        for (Wall wall : walls) {
            if (object.getBoundsInParent().intersects(wall.getImageView().getBoundsInParent())) {
                if (object.getImage().getUrl().endsWith("Bullet.png")) {
                    if (root.getChildren().contains(wall.getImageView())) {
                        if (!wall.getClass().getSimpleName().equals("MetalWall")) {
                            walls.remove(wall);
                            root.getChildren().remove(wall.getImageView());
                        }

                        return true;
                    }
                }

            }
        }

        return false;
    }

    public boolean destroyWalls(Tank object, double dx, double dy, Pane root) {
        object.getImageView().setLayoutX(object.getImageView().getLayoutX() + dx);
        object.getImageView().setLayoutY(object.getImageView().getLayoutY() + dy);
//        ImageView imageView = new ImageView(new Image(object.getImageView().getImage().getUrl()));
//        imageView.setY(object.getLayoutY());
//        imageView.setX(object.getLayoutX());

        for (Wall wall : walls) {
            if (object.getImageView().getBoundsInParent().intersects(wall.getImageView().getBoundsInParent())) {
                if (object.getImageView().getImage().getUrl().endsWith("Bullet.png")) {
                    if (root.getChildren().contains(wall.getImageView())) {
                        //wall.lostHP();
                        root.getChildren().remove(wall.getImageView());
                        return true;
                    }
                }

            }
        }

        return false;
    }


    public boolean destroy(ImageView bullet, Pane root) {
        Bounds bounds1 = bullet.getBoundsInParent();
        for (int i = 0; i < this.tanks.size(); i++) {
            Bounds bounds2 = this.tanks.get(i).getImageView().getBoundsInParent();
            if (bounds1.intersects(bounds2) && tanks.get(i).getClass().getSimpleName().equals("Player")) {
                tanks.get(i).lostHP();
                System.out.println("Player");
                System.out.println(tanks.get(i).getHealth());
                if (tanks.get(i).getHealth() <= 0) {
                    explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                            "\\explode.png", 20, 20, true, true));
                    double xPos = tanks.get(i).getXPos();
                    double yPos = tanks.get(i).getYPos();
                    explosion.setX(xPos);
                    explosion.setY(yPos);
                    root.getChildren().add(explosion);
                    tanks.remove(tanks.get(i));
                    new Explosion(explosion).explosionAnimation(xPos, yPos, root);
                    return true;
                }
                return true;
            }
        }
        return false;
    }
}
