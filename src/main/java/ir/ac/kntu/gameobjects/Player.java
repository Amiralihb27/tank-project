package ir.ac.kntu.gameobjects;

import ir.ac.kntu.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.awt.font.GlyphMetrics;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;

import static ir.ac.kntu.constants.GlobalConstants.canvasHeight;
import static ir.ac.kntu.constants.GlobalConstants.canvasWidth;


public class Player extends Tank {

    private GraphicsContext gc;

    private int playerSize = 25;

    private Game game;

    private ArrayList<Tank> tanks = new ArrayList<>();

    public int getPlayerSize() {
        return playerSize;
    }

    public void setPlayerSize(int playerSize) {
        this.playerSize = playerSize;
    }

    public Player() {

    }

    public Player(double xPos, double yPos, ImageView imageView) {
        super(xPos, yPos, imageView);
        super.setHealth(3);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(super.getImageView().getImage(), super.getXPos(), super.getYPos(), playerSize, playerSize);
    }

    @Override
    public void lostHP() {
        super.setXPos(canvasWidth/3);
        super.setYPos(canvasHeight - playerSize);
        super.lostHP();
        if (getHealth() <=0 ) {
            game.showResult();
        } else {
            draw(gc);
        }

    }

    public void die(){
        setHealth(0);
        lostHP();
    }

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setImage(Image image) {
        super.getImageView().setImage(image);
    }

    public void move(Scene scene, GraphicsContext gc, Collision collision) {
        Image temp = super.getImageView().getImage();
        draw(gc);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft(collision);
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight(collision);
            } else if (event.getCode() == KeyCode.UP) {
                setImage(temp);
                moveUp(collision);
            } else if (event.getCode() == KeyCode.DOWN) {
                moveDown(collision);
            } else if (event.getCode() == KeyCode.SPACE && !super.getBullet().isAlive()) {
                super.getBullet().setShooting(true);
            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                super.getBullet().setShooting(false);
            }
        });
    }


    public void moveUp(Collision collision) {
        super.getBullet().setAngle(90.0);
        super.setDirection(Direction.UP);
        super.getBullet().initializeTheDirection(super.getDirection());
        //  Collision collision = new Collision(walls);

        if (getYPos() + getSpeedY() > 0 && !collision.checkCollision(this, 0, super.getSpeedY() * -1)) {
            super.setYPos(super.getYPos() - super.getSpeedY());
            super.getImageView().setY(super.getYPos());
        }
    }

    public void moveDown(Collision collision) {
        super.getBullet().setAngle(270.0);
        Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank3.png");
        setImage(playerImage2);
        super.setDirection(Direction.DOWN);
        super.getBullet().initializeTheDirection(super.getDirection());
        //Collision collision = new Collision(walls);
        if (getYPos() + getPlayerSize() < canvasHeight &&
                !collision.checkCollision(this, 0, super.getSpeedY())) {
            super.setYPos(super.getYPos() + super.getSpeedY());
            super.getImageView().setY(super.getYPos());
        }


    }

    public void moveLeft(Collision collision) {
        super.getBullet().setAngle(180.0);
        Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank2.png");
        setImage(playerImage2);
        super.setDirection(Direction.LEFT);
        super.getBullet().initializeTheDirection(super.getDirection());
        //Collision collision = new Collision(walls);
        if (getXPos() - getSpeedX() > 0 && !collision.checkCollision(this, super.getSpeedX() * -1, 0)) {
            super.setXPos(super.getXPos() - super.getSpeedX());
            super.getImageView().setX(super.getXPos());
        }
    }

    public void moveRight(Collision collision) {
        super.getBullet().setAngle(0.0);
        Image playerImage2 = new Image("F:\\project4\\src\\main\\resources\\images\\tank4.png");
        setImage(playerImage2);
        super.setDirection(Direction.RIGHT);
        super.getBullet().initializeTheDirection(super.getDirection());
        //Collision collision = new Collision(walls);

        if (getXPos() + playerSize + getSpeedX() < canvasWidth &&
                !collision.checkCollision(this, super.getSpeedX(), 0)) {
            super.setXPos(super.getXPos() + super.getSpeedX());
            super.getImageView().setX(super.getXPos());
        }
    }


    public ImageView makeCopy() {
        ImageView copy = new ImageView();
        double angle = getBullet().getAngle();
        int dx = 0;
        int dy = 0;
        if (angle == 0.0) {
            dx += playerSize / 2;
            dy += playerSize / 2;
        } else if (angle == 270.0) {
            dy += playerSize / 2;
            dx += playerSize / 2;
        } else if (angle == 180.0) {
            dy += playerSize / 2;
        }
        copy.setX(getImageView().getX() + dx);
        copy.setY(getImageView().getY() + dy);
        copy.setImage(getImageView().getImage());
        return copy;
    }

    public void addDestroyedTanks(Tank tank) {
        this.tanks.add(tank);
    }

    public ArrayList<OrdinaryTank> ordinaryTanks() {
        ArrayList<OrdinaryTank> ordinaryTanks = new ArrayList<>();
        for (Tank tank : tanks) {
            if (tank.getClass().getSimpleName().equals("OrdinaryTank")) {
                OrdinaryTank ordinaryTank = (OrdinaryTank) tank;
                ordinaryTanks.add(ordinaryTank);
            }
        }
        return ordinaryTanks;
    }

    public ArrayList<ShieldTank> shieldTanks() {
        ArrayList<ShieldTank> shieldTank = new ArrayList<>();
        for (Tank tank : tanks) {
            if (tank.getClass().getSimpleName().equals("ShieldTank")) {
                ShieldTank shieldTank1 = (ShieldTank) tank;
                shieldTank.add(shieldTank1);
            }
        }
        return shieldTank;
    }

    public ArrayList<RandomTank> ordinaryRandom() {
        ArrayList<RandomTank> randomTanks = new ArrayList<>();
        for (Tank tank : tanks) {
            if (tank.getClass().getSimpleName().equals("RandomTank")) {
                RandomTank randomTank = (RandomTank) tank;
                if(randomTank.getName().equals("OrdinaryTank")){
                    randomTanks.add(randomTank);
                }

            }
        }
        return randomTanks;
    }

    public ArrayList<RandomTank> shieldRandom() {
        ArrayList<RandomTank> randomTanks = new ArrayList<>();
        for (Tank tank : tanks) {
            if (tank.getClass().getSimpleName().equals("RandomTank")) {
                RandomTank randomTank = (RandomTank) tank;
                if(randomTank.getName().equals("ShieldTank")){
                    randomTanks.add(randomTank);
                }

            }
        }
        return randomTanks;
    }




}
