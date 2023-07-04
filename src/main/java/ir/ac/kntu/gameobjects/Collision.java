package ir.ac.kntu.gameobjects;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

import static ir.ac.kntu.constants.GlobalConstants.*;

public class Collision {


    private ArrayList<Wall> walls = new ArrayList<>();


    private ArrayList<Tank> tanks = new ArrayList<>();

    private ImageView explosion;

    private Flag flag;

    private SpecialPowers specialPowers;

    private Pane root;


    public Collision(ArrayList<Wall> walls) {
        this.walls = walls;
    }


    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(ArrayList<Tank> tanks) {
        this.tanks = tanks;
    }

    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public void setRoot(Pane root) {
        this.root = root;
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    public void setSpecialPowers(Pane root, RandomTank randomTank) {
        this.specialPowers = randomTank.getSpecialPowers();
        boolean isFull = true;
        while (isFull) {
            isFull = checkToAddSpecialPower(randomTank);
        }
        root.getChildren().add(randomTank.getSpecialPowers().getImageView());
        Timeline start = new Timeline(new KeyFrame(Duration.seconds(randomTank.getSpecialPowers().getTime()), event -> {
            root.getChildren().remove(randomTank.getSpecialPowers().getImageView());
        }));
        start.setCycleCount(1);
        start.play();
    }

    public boolean checkToAddSpecialPower(RandomTank randomTank) {
        Random random = new Random();
        randomTank.getSpecialPowers().setxPos(random.nextDouble(canvasWidth - tankSize));
        randomTank.getSpecialPowers().setyPos(random.nextDouble(canvasHeight - tankSize));
        Bounds bounds = randomTank.getSpecialPowers().getImageView().getBoundsInParent();
        for (Wall wall : walls) {
            if (bounds.intersects(wall.getImageView().getBoundsInParent())) {
                return true;
            }
        }

        for (Tank other : this.tanks) {
            if (bounds.intersects(other.getImageView().getBoundsInParent())) {
                return true;
            }
        }

        return false;

    }

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
        if (this.specialPowers != null && tank.getClass().getSimpleName().equals("Player")) {
            if (copy.getBoundsInParent().intersects(specialPowers.getImageView().getBoundsInParent()) &&
                    root.getChildren().contains(specialPowers.getImageView())) {
                root.getChildren().remove(specialPowers.getImageView());
                specialPowers.addBuff((Player) tank);
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

    public boolean destroy(ImageView bullet, Pane root) {
        Bounds bounds1 = bullet.getBoundsInParent();
        checkForFlag(bounds1);
        for (int i = 0; i < this.tanks.size(); i++) {
            Bounds bounds2 = this.tanks.get(i).getImageView().getBoundsInParent();
            if (bounds1.intersects(bounds2) && tanks.get(i).getClass().getSimpleName().equals("Player")) {
                double deadPosX= tanks.get(i).getXPos();
                double deadPosY=tanks.get(i).getYPos();
                tanks.get(i).lostHP();
                explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                        "\\explode.png", 20, 20, true, true));
                explosion.setX(deadPosX);
                explosion.setY(deadPosY);
                root.getChildren().add(explosion);
                new Explosion(explosion).explosionAnimation(deadPosX, deadPosY, root);
                System.out.println("Health: " + tanks.get(i).getHealth());
                if (tanks.get(i).getHealth() <= 0) {
                    tanks.remove(tanks.get(i));
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    public void checkForFlag(Bounds bullet) {
        Bounds object = this.flag.getImageView().getBoundsInParent();
        for (Tank tank : tanks) {
            if (tank.getClass().getSimpleName().equals("Player")) {
                Player player = (Player) tank;
                if (bullet.intersects(object)) {
                    player.die();
                }
            }
        }

    }
}
