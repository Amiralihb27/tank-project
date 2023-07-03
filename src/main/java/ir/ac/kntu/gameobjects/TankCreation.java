package ir.ac.kntu.gameobjects;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static ir.ac.kntu.constants.GlobalConstants.tankSize;

public class TankCreation {

    private ArrayList<Tank> tanks;

    private ArrayList<Wall> walls;

    private ArrayList<OrdinaryTank> ordinaryTanks = new ArrayList<>();


    private ArrayList<ShieldTank> shieldTanks = new ArrayList<>();


    private int[] indexes = {0, 0, 0};

    int numberOfTotalTanks;

    public TankCreation(ArrayList<Tank> tanks, ArrayList<Wall> walls) {
        this.tanks = tanks;
        this.walls = walls;
    }

    public int getNumberOfTotalTanks() {
        return numberOfTotalTanks;
    }

    public void setNumberOfTotalTanks(int numberOfTotalTanks) {
        this.numberOfTotalTanks = numberOfTotalTanks;
    }

    public void creatingEnemy(Pane root,Flag flag) {
        for (int i = 0; i < numberOfTotalTanks / 2; i++) {
            OrdinaryTank ordinaryTank = new OrdinaryTank(0, 0,
                    new ImageView(new Image("F:\\javap for 4012\\FilePractice\\enemytank1.png", tankSize,
                            tankSize, true, true)));
            ordinaryTanks.add(ordinaryTank);
            ShieldTank shieldTank = new ShieldTank(0, 0,
                    new ImageView(new Image("F:\\project4\\src\\main\\resources\\images" +
                            "\\enemyupmetal.png", tankSize,
                            tankSize, true, true)));
            shieldTanks.add(shieldTank);
        }
        handlingTanks(root,flag);
    }


    public void handlingTanks(Pane root,Flag flag) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (tanks.size() < 5) {
                    handlingOrdinary(root,flag);
                }
            }
        };
        timer.start();
    }


    public void handlingOrdinary(Pane root,Flag flag) {
        Bullet newBullet = new Bullet(0, 0);
        Collision collision = new Collision(walls);
        collision.setTanks(tanks);
        collision.setFlag(flag);
        if (indexes[0] < ordinaryTanks.size()) {
            root.getChildren().add(ordinaryTanks.get(indexes[0]).getImageView());
            tanks.add(ordinaryTanks.get(indexes[0]));
            ordinaryTanks.get(indexes[0]).setBullet(newBullet);
            ordinaryTanks.get(indexes[0]).initializeDirection(tankSize, collision);
            if (root.getChildren().contains(ordinaryTanks.get(indexes[0]).getImageView())) {
                ordinaryTanks.get(indexes[0]).shootBullet(root, walls, tanks,collision.getFlag());
            }
            indexes[0]++;
        }

        handlingShield(root, newBullet, collision);

    }

    public void handlingShield(Pane root, Bullet newBullet, Collision collision) {
        if (indexes[1] < shieldTanks.size()) {
            if (tanks.size() < 5) {
                root.getChildren().add(shieldTanks.get(indexes[1]).getImageView());
                tanks.add(shieldTanks.get(indexes[1]));
                newBullet = new Bullet(0, 0);
                shieldTanks.get(indexes[1]).setBullet(newBullet);
                shieldTanks.get(indexes[1]).initializeDirection(tankSize, collision);
                if (root.getChildren().contains(shieldTanks.get(indexes[1]).getImageView())) {
                    shieldTanks.get(indexes[1]).shootBullet(root, walls, tanks,collision.getFlag());
                }
                indexes[1]++;
            }
        }
    }

}
