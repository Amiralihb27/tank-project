package ir.ac.kntu;

import ir.ac.kntu.constants.GlobalConstants;
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
import java.util.Scanner;

import static ir.ac.kntu.constants.GlobalConstants.canvasHeight;
import static ir.ac.kntu.constants.GlobalConstants.canvasWidth;


public class Game extends Application {

    private static int width;

    private static int heidth;


    private static final int PLAYER_SIZE = GlobalConstants.getTankSize();

    private ArrayList<Wall> walls = new ArrayList<>();

    private Scene scene;


    private Flag flag;

    private boolean isWinning = false;

    private int numberOfTotalTanks = 10;

    private int[] indexes = {0, 0, 0};

    private int clicked = 0;


    private static int bulletsize = 15 / 25 * PLAYER_SIZE;


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
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Width of the scene: ");
        int sceneWidth = sc.nextInt();
        System.out.println("Enter the Height of the scene: ");
        int height = sc.nextInt();
        GlobalConstants globalConstants = new GlobalConstants(sceneWidth, height);
        width = canvasWidth;
        heidth = canvasHeight;
        launch(args);
    }


    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
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
        canvas = new Canvas(width, heidth);
        gc = canvas.getGraphicsContext2D();
        Rectangle rect = new Rectangle(0, 0, width, heidth);
        root.setStyle("-fx-background-color: black;");
        rect.setFill(null);
        rect.setStroke(Color.RED);
        rect.setStrokeWidth(5);
        root.getChildren().add(rect);
        Place place = new Place();
        ImageView flagImage = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\flag.png",
                PLAYER_SIZE, PLAYER_SIZE, true, true));
        flag = new Flag(canvasWidth / 2, canvasHeight - PLAYER_SIZE, flagImage);
        place.addBrickToTheTop(root, PLAYER_SIZE, walls, flag);
        Collision collision = new Collision(walls);
        collision.setTanks(tanks);
        collision.setFlag(flag);
        collision.setRoot(root);
        handlingTanks(collision);
        scene = new Scene(root, width, heidth);
        scene.setFill(Color.BLACK);
        player.setGame(this);
        player.move(scene, gc, collision);
        primaryStage.setTitle("Player Shoot Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        shooting(gc, collision);
    }


    public void handlingTanks(Collision collision) {
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\tank1.png"));
        player = new Player(canvasWidth / 3, canvasHeight - PLAYER_SIZE, imageView);
        //player.setYPos(600 - player.getPlayerSize());
        player.setGc(gc);
        bullet = new Bullet(0, 0);
        //  bulletAngle = 90.0;
        player.setBullet(bullet);
        root.getChildren().add(canvas);
        tanks.add(player);
        creatingEnemy();
        shooting(gc, collision);

    }

    public void creatingEnemy() {
        TankCreation tankCreation = new TankCreation(tanks, walls);
        tankCreation.setNumberOfTotalTanks(numberOfTotalTanks);
        tankCreation.creatingEnemy(root, this.flag);
    }


    public void shooting(GraphicsContext gc, Collision collision) {
        AnimationTimer timer = new AnimationTimer() {
            long lastTime = System.nanoTime();

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 50000000.0;
                lastTime = now;
                gc.clearRect(0, 0, canvasWidth, canvasHeight);
                player.draw(gc);
                Bullet newBullet = player.getBullet();
                // tanks.get(0).shootBullet(gc,tanks,obstaclesGroup,root);
                if (newBullet.isShooting()) {
                    setPosWhileShooting(newBullet);
                }
                if (newBullet.isAlive()) {
                    newBullet.update(deltaTime);
                    newBullet.draw(gc);
                    checkCollisionForBullet(newBullet, collision);

                }
            }
        };
        timer.start();
    }

    public void checkCollisionForBullet(Bullet newBullet, Collision collision) {
        //Collision collision = new Collision(walls);
        ImageView bulletImageView = new ImageView(newBullet.getBulletImage());
        bulletImageView.setY(newBullet.getyPos());
        bulletImageView.setX(newBullet.getxPos());
        if (collision.destroyWalls(bulletImageView, newBullet.getSpeedX(),
                newBullet.getSpeedY(), root) || destroy(bulletImageView, collision)) {
            ImageView explosion = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                    "\\explode.png", 20, 20, true, true));
            explosion.setX(bulletImageView.getX());
            explosion.setY(bulletImageView.getY());
            root.getChildren().add(explosion);
            newBullet.kill();
            if (tanks.size() <= 1) {
                this.isWinning = true;
                showResult();
            }
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
                root.getChildren().remove(explosion); // Remove the image from the root pane
            }));
            timeline.setCycleCount(3);
            timeline.play();
        }
        if (newBullet.getxPos() < 0 || newBullet.getxPos() > canvasWidth - newBullet.getBulletSize() ||
                newBullet.getyPos() < 0 || newBullet.getyPos() > canvasHeight - newBullet.getBulletSize()
                || Math.abs(newBullet.getxPos() - newBullet.getStartingX()) >= 200 ||
                Math.abs(newBullet.getyPos() - newBullet.getStartingY()) >= 200) {
            newBullet.kill();
        }
    }

    public boolean destroy(ImageView bullet, Collision collision) {
        Bounds bounds1 = bullet.getBoundsInParent();
        for (int i = 0; i < this.tanks.size(); i++) {
            Bounds bounds2 = this.tanks.get(i).getImageView().getBoundsInParent();
            if (bounds1.intersects(bounds2) && !this.tanks.get(i).getClass().getSimpleName().equals("Player")) {
                tanks.get(i).lostHP(player.getPowerOfTheBullet());
                if (tanks.get(i).getHealth() <= 0) {
                    if (!tanks.get(i).getClass().getSimpleName().equals("Player")) {
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
                        powerUp(tanks.get(i),collision);
                        tanks.remove(tanks.get(i));
                        new Explosion(explosion).explosionAnimation(xPos, yPos, root);
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public void powerUp(Tank tank,Collision collision){
        if (tank.getClass().getSimpleName().equals("RandomTank")) {
            collision.setSpecialPowers(root, (RandomTank) tank);
        }
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
                    BufferedReader reader = new BufferedReader(new FileReader(new File("Users.txt")));
                    StringBuilder content = new StringBuilder();
                    String line;
                    int currentLine = 0;
                    while ((line = reader.readLine()) != null) {
                        if (currentLine == lineNumber) {
                            content.append(user.getUserName()).append(" ").append(user.getPassWord()).append(" ")
                                    .append(0).append(" ").append(user.getHighScore()).append(" ")
                                    .append(user.getNumberOfMatches())
                                    .append(System.lineSeparator());
                        } else {
                            content.append(line).append(System.lineSeparator());
                        }
                        currentLine++;
                    }
                    reader.close();
                    FileWriter writer = new FileWriter(new File("Users.txt"));
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
