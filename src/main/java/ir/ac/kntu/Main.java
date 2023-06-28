package ir.ac.kntu;

import ir.ac.kntu.gameobjects.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

import static javafx.application.Application.launch;

public class Main extends Application {

    private static final int WIDTH = 600;

    private static final int HEIGHT = 600;


    private static final int PLAYER_SIZE = 30;


    private static final int BULLET_SIZE = 20;


    private static final double BULLET_SPEED = 5.0;

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

        rect.setFill(null);
        rect.setStroke(Color.RED);
        rect.setStrokeWidth(5);
        root.getChildren().add(rect);
        handlingTanks();
        BrickWall brickWall = new BrickWall(0, 0, new ImageView(new Image(
                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", player.getPlayerSize(),
                player.getPlayerSize(), true, true)));
//       root.getChildren().add(brickWall.getImageView());
//        root.getChildren().add(brickWall.getImageView());
        Place place = new Place();
        place.addBrickToTheTop(root, player.getPlayerSize());
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        player.move(scene, gc);
        primaryStage.setTitle("Player Shoot Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        shooting(gc);
    }

    public void handlingTanks() {
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\tank1.png"));
        player = new Player(200, 350, imageView);
        bullet = new Bullet(0, 0);
        //  bulletAngle = 90.0;
        player.setBullet(bullet);
        root.getChildren().add(canvas);
        OrdinaryTank ordinaryTank = new OrdinaryTank(0, 0,
                new ImageView(new Image("F:\\javap for 4012\\FilePractice\\enemytank1.png", player.getPlayerSize(),
                        player.getPlayerSize(), true, true)));
        root.getChildren().add(ordinaryTank.getImageView());
        ordinaryTank.initializeDirection(player.getPlayerSize(), root);
    }


    public void shooting(GraphicsContext gc) {
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
                    if (newBullet.getxPos() < 0 || newBullet.getxPos() > 600 - newBullet.getBulletSize() ||
                            newBullet.getyPos() < 0 || newBullet.getyPos() > 600 - newBullet.getBulletSize()
                            || Math.abs(newBullet.getxPos() - newBullet.getStartingX()) >= 200 ||
                            Math.abs(newBullet.getyPos() - newBullet.getStartingY()) >= 200) {
                        newBullet.kill();
                    }
                }
            }
        };
        timer.start();
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
