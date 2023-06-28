package ir.ac.kntu;

import ir.ac.kntu.gameobjects.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Main extends Application {

    private static final int WIDTH = 600;

    private static final int HEIGHT = 600;


    private static final int PLAYER_SIZE = 30;


    private static final int BULLET_SIZE = 20;


    private static final double BULLET_SPEED = 5.0;

    private ArrayList<Tank> tanks = new ArrayList<>();

    private Pane root;

    private Canvas canvas;

    private GraphicsContext gc;

    private Player player;

    private Bullet bullet;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        root = new Pane();
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Rectangle rect = new Rectangle(0, 0, 600, 600);
        Group obstaclesGroup = new Group();
        rect.setFill(null);
        rect.setStroke(Color.RED);
        rect.setStrokeWidth(5);
        root.getChildren().add(rect);
        handlingTanks(obstaclesGroup);
        BrickWall brickWall = new BrickWall(0, 0, new ImageView(new Image(
                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", player.getPlayerSize(),
                player.getPlayerSize(), true, true)));
        Place place = new Place();
        place.addBrickToTheTop(root, player.getPlayerSize(), obstaclesGroup);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        player.move(scene, gc, obstaclesGroup, root);
        primaryStage.setTitle("Player Shoot Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        shooting(gc, obstaclesGroup);
    }

    public void handlingTanks(Group obstaclesGroup) {
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\tank1.png"));
        player = new Player(400, 600, imageView);
        player.setYPos(600 - player.getPlayerSize());
        bullet = new Bullet(0, 0);
        //  bulletAngle = 90.0;
        player.setBullet(bullet);
        root.getChildren().add(canvas);
        OrdinaryTank ordinaryTank = new OrdinaryTank(0, 0,
                new ImageView(new Image("F:\\javap for 4012\\FilePractice\\enemytank1.png", player.getPlayerSize(),
                        player.getPlayerSize(), true, true)));
        root.getChildren().add(ordinaryTank.getImageView());
        this.tanks.add(ordinaryTank);
        ordinaryTank.initializeDirection(player.getPlayerSize(), obstaclesGroup);
    }


    public void shooting(GraphicsContext gc, Group obstaclesGroup) {
        AnimationTimer timer = new AnimationTimer() {
            long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 50000000.0;
                lastTime = now;
                gc.clearRect(0, 0, 600, 600);
                player.draw(gc);
                Bullet newBullet = player.getBullet();
                if (newBullet.isShooting()) {
                    setPosWhileShooting(newBullet);
                }
                if (newBullet.isAlive()) {
                    newBullet.update(deltaTime);
                    newBullet.draw(gc);
                    checkCollisionForBullet(newBullet, obstaclesGroup);

                }
            }
        };
        timer.start();
    }

    public void checkCollisionForBullet(Bullet newBullet, Group obstaclesGroup) {
        Collision collision = new Collision(obstaclesGroup);
        ImageView bulletImageView = new ImageView(newBullet.getBulletImage());
        bulletImageView.setY(newBullet.getyPos());
        bulletImageView.setX(newBullet.getxPos());
        if (collision.checkCollision(bulletImageView, newBullet.getSpeedX(),
                newBullet.getSpeedY(), root) || destroy(bulletImageView)) {
            collision.destroy(root, bulletImageView);
            newBullet.kill();
        }
        if (newBullet.getxPos() < 0 || newBullet.getxPos() > 600 - newBullet.getBulletSize() ||
                newBullet.getyPos() < 0 || newBullet.getyPos() > 600 - newBullet.getBulletSize()
                || Math.abs(newBullet.getxPos() - newBullet.getStartingX()) >= 200 ||
                Math.abs(newBullet.getyPos() - newBullet.getStartingY()) >= 200) {
            newBullet.kill();
        }
    }

    public boolean destroy(ImageView bullet) {
        Bounds bounds1 = bullet.getBoundsInParent();
        for (int i = 0; i < this.tanks.size(); i++) {
            Bounds bounds2 = this.tanks.get(i).getImageView().getBoundsInParent();
            if (bounds1.intersects(bounds2)) {
                root.getChildren().remove(tanks.get(i));
                tanks.remove(tanks.get(i));
                return true;
            }
        }
        return false;
    }

    public void setPosWhileShooting(Bullet newBullet) {
        newBullet.setStartingX(player.getXPos()
                + player.getPlayerSize() / 2 - newBullet.getBulletSize() / 2);
        newBullet.setxPos(newBullet.getStartingX());
        newBullet.setStartingY(player.getYPos() + player.getPlayerSize() / 2 - newBullet.getBulletSize() / 2);
        newBullet.setyPos(newBullet.getStartingY());
        newBullet.setSpeedX(Math.cos(Math.toRadians(newBullet.getAngle())) * newBullet.getBulletSpeed());
        newBullet.setSpeedY(-Math.sin(Math.toRadians(newBullet.getAngle())) * newBullet.getBulletSpeed());
        newBullet.draw(gc);
    }


}
