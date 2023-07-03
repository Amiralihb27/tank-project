package ir.ac.kntu;

import ir.ac.kntu.gameobjects.*;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class Game extends Application {

    private static final int WIDTH = 600;

    private static final int HEIGHT = 600;


    private static final int PLAYER_SIZE = 25;

    private ArrayList<Wall> walls = new ArrayList<>();


    private GameState gameState = GameState.RESUMED;

    private Scene scene;


    private boolean isWinning = false;

    private int numberOfTotalTanks = 10;

    private int[] indexes = {0, 0, 0};

    private int clicked = 0;


    private static final int BULLET_SIZE = 25;


    private static final double BULLET_SPEED = 5.0;


    private User user;

    private ImageView explosion;

    private ArrayList<Tank> tanks = new ArrayList<>();

    private Pane root;

    private Canvas canvas;

    private GraphicsContext gc;

    private Player player;

    private Bullet bullet;

    private Stage stage;

    private Menu menu;

    public static void main(String[] args) {
        launch(args);
    }


    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfTotalTanks() {
        return numberOfTotalTanks;
    }

    public void setNumberOfTotalTanks(int numberOfTotalTanks) {
        this.numberOfTotalTanks = numberOfTotalTanks;
    }

    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        // Initialize the menu
        menu = new Menu(stage);
        menu.setGame(this);
        // Set the key event handler for the menu
        menu.show();
        menu.getScene().setOnKeyPressed(this::handleKeyPress);
        // Show the menu initially
        // stage.setScene(menu.getScene());
    }

    public void handleKeyPress(KeyEvent event) {
        // Pass the key event to the menu for handling
        menu.handleKeyPress(event);
    }

    public void startGame(Stage primaryStage) {
        root = new Pane();
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        Rectangle rect = new Rectangle(0, 0, 600, 600);
        root.setStyle("-fx-background-color: black;");
        // Group obstaclesGroup = new Group();
        rect.setFill(null);
        rect.setStroke(Color.RED);
        rect.setStrokeWidth(5);
        root.getChildren().add(rect);
        Place place = new Place();
        place.addBrickToTheTop(root, PLAYER_SIZE, walls);
        handlingTanks();
        scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        player.setGame(this);
        Collision collision = new Collision(walls);
        collision.setTanks(tanks);
        player.move(scene, gc, collision);
        primaryStage.setTitle("Player Shoot Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        shooting(gc);


    }


    public void handlingTanks() {
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\tank1.png"));
        player = new Player(400, 600, imageView);
        player.setYPos(600 - player.getPlayerSize());
        player.setGc(gc);
        bullet = new Bullet(0, 0);
        //  bulletAngle = 90.0;
        player.setBullet(bullet);
        root.getChildren().add(canvas);
        tanks.add(player);
        creatingEnemy();
        shooting(gc);

    }

    public void creatingEnemy() {
        TankCreation tankCreation = new TankCreation(tanks, walls);
        tankCreation.setNumberOfTotalTanks(numberOfTotalTanks);
        tankCreation.creatingEnemy(root);
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
                // tanks.get(0).shootBullet(gc,tanks,obstaclesGroup,root);
                if (newBullet.isShooting()) {
                    setPosWhileShooting(newBullet);
                }
                if (newBullet.isAlive()) {
                    newBullet.update(deltaTime);
                    newBullet.draw(gc);
                    checkCollisionForBullet(newBullet);

                }
            }
        };
        timer.start();
    }

    public void checkCollisionForBullet(Bullet newBullet) {
        Collision collision = new Collision(walls);
        ImageView bulletImageView = new ImageView(newBullet.getBulletImage());
        bulletImageView.setY(newBullet.getyPos());
        bulletImageView.setX(newBullet.getxPos());
        if (collision.destroyWalls(bulletImageView, newBullet.getSpeedX(),
                newBullet.getSpeedY(), root) || destroy(bulletImageView)) {
            ImageView explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                    "\\explode.png", 20, 20, true, true));
            explosion.setX(bulletImageView.getX());
            explosion.setY(bulletImageView.getY());
            root.getChildren().add(explosion);
            newBullet.kill();
            if (tanks.size() <=1 ) {
                this.isWinning=true;
                showResult();
            }
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                root.getChildren().remove(explosion); // Remove the image from the root pane
            }));
            timeline.setCycleCount(3);
            timeline.play();
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
            if (bounds1.intersects(bounds2) && !this.tanks.get(i).getClass().getSimpleName().equals("Player")) {
                tanks.get(i).lostHP();
                if (tanks.get(i).getHealth() <= 0) {
                    if (!tanks.get(i).getClass().getSimpleName().equals("Player")) {
                        player.addScore(tanks.get(i).getScore());
                        player.addDestroyedTanks(tanks.get(i));
                        user.addScore(tanks.get(i).getScore());
                        explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                                "\\explode.png", 20, 20, true, true));
                        double xPos = tanks.get(i).getXPos();
                        double yPos = tanks.get(i).getYPos();
                        explosion.setX(xPos);
                        explosion.setY(yPos);
                        root.getChildren().add(explosion);
                        root.getChildren().remove(tanks.get(i).getImageView());
                        tanks.remove(tanks.get(i));
                        System.out.println(tanks.size());
                        new Explosion(explosion).explosionAnimation(xPos, yPos, root);
                        return true;
                    }
                }
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


    public void stop() {
        if (this.user != null) {
            Platform.runLater(() -> {
                int lineNumber = user.getCurrentLine(); // Specify the line number where you want to add the text
                try {
                    File file = new File("Users.txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    StringBuilder content = new StringBuilder();
                    String line;
                    int currentLine = 0;
                    while ((line = reader.readLine()) != null) {
                        if (currentLine == lineNumber) {
                            content.append(user.getUserName()).append(" ").append(user.getPassWord()).append(" ")
                                    .append(0).append(" ").append(user.getHighScore()).append(System.lineSeparator());
                        } else {
                            content.append(line).append(System.lineSeparator());
                        }
                        currentLine++;
                    }
                    reader.close();
                    FileWriter writer = new FileWriter(file);
                    writer.write(content.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void showResult() {
        ShowScores showScores = new ShowScores(stage, scene, this.player, user);
        showScores.setWinning(this.isWinning);
        showScores.showScreen();

    }

}