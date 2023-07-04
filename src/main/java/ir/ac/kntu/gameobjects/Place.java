package ir.ac.kntu.gameobjects;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import static ir.ac.kntu.constants.GlobalConstants.canvasHeight;
import static ir.ac.kntu.constants.GlobalConstants.canvasWidth;

public class Place {


    public void addBrickToTheTop(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(2 * canvasWidth / 15, canvasHeight / 20 + 10,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j);
                brickWall.drawWall(root);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(canvasWidth - 4 * canvasWidth / 15, canvasHeight / 20 + 10,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j);
                brickWall.drawWall(root);
            }
        }
        addBrickToTheBot(root, size, walls, flag);
    }

    public void addBrickToTheBot(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(2 * canvasWidth / 15,
                        canvasHeight - (3 * canvasHeight / 20 + 10 + 6 * size),
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j);
                brickWall.drawWall(root);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(canvasWidth - 4 * canvasWidth / 15,
                        canvasHeight - (3 * canvasHeight / 20 + 10 + 6 * size),
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j);
                brickWall.drawWall(root);
            }
        }
        addMetalToTheTop(root, size, walls, flag);
    }

    public void addMetalToTheTop(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                MetalWall metalWall = new MetalWall(2 * canvasWidth / 15 + 5 * size, canvasHeight / 20 + 10,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackmetal.png", size,
                                size, true, true)));
                walls.add(metalWall);
                if (i == 1) {
                    metalWall.getImageView().setX(canvasWidth - 6 * canvasWidth / 15);
                } else {
                    metalWall.getImageView().setX(metalWall.getImageView().getX() + 6.5 * size * i);
                }
                metalWall.getImageView().setY(metalWall.getImageView().getY() + size * j);
                metalWall.drawWall(root);
            }
        }
        addMetalToTheBot(root, size, walls, flag);
    }

    public void addMetalToTheBot(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                MetalWall metalWall = new MetalWall(2 * canvasWidth / 15 + 5 * size,
                        canvasHeight - (3 * canvasHeight / 20 + 10 + 6 * size),
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackmetal.png", size,
                                size, true, true)));
                walls.add(metalWall);
                if (i == 1) {
                    metalWall.getImageView().setX(canvasWidth - 6 * canvasWidth / 15);
                } else {
                    metalWall.getImageView().setX(metalWall.getImageView().getX() + 6.5 * size * i);
                }
                metalWall.getImageView().setY(metalWall.getImageView().getY() + size * j);
                metalWall.drawWall(root);
            }
        }
        addWallToTheMid(root, size, walls, flag);
    }

    public void addWallToTheMid(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {

                MetalWall metalWall = new MetalWall(canvasWidth / 2 - size - 2 * size / 3 + (2 * j * 4 * size / 3),
                        canvasHeight / 2 - 3 * size + i * size,
                        new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\stackmetal.png",
                                size, size, true, true)));
                metalWall.getImageView().setX(metalWall.getImageView().getX());
                metalWall.getImageView().setY(metalWall.getImageView().getY());
                walls.add(metalWall);
                root.getChildren().add(metalWall.getImageView());
            }

            BrickWall brickWall = new BrickWall(canvasWidth / 2 - size / 3,
                    canvasHeight / 2 - 3 * size + i * size,
                    new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\stackbrick.png",
                            size, size, true, true)));
            brickWall.getImageView().setX(brickWall.getImageView().getX());
            brickWall.getImageView().setY(brickWall.getImageView().getY());
            walls.add(brickWall);
            root.getChildren().add(brickWall.getImageView());
        }

        addFlag(root, size, walls, flag);
    }


    public void addFlag(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        ImageView flagImage = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\flag.png",
                size, size, true, true));
        flag = new Flag(canvasWidth / 2, canvasHeight - size, flagImage);

        root.getChildren().add(flagImage);
        addProtectionToFlag(root, size, walls, flag);
    }

    public void addProtectionToFlag(Pane root, int size, ArrayList<Wall> walls, Flag flag) {
        ImageView flagImage = flag.getImageView();
        BrickWall[] brickWalls = new BrickWall[3];
        brickWalls[0] = new BrickWall(2 * canvasWidth / 15, canvasHeight / 20 + 10,
                new ImageView(new Image(
                        "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                        size, true, true)));
        brickWalls[0].getImageView().setX(flagImage.getX() + size);
        brickWalls[0].getImageView().setY(flagImage.getY());
        brickWalls[1] = new BrickWall(2 * canvasWidth / 15, canvasHeight / 20 + 10,
                new ImageView(new Image(
                        "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                        size, true, true)));
        brickWalls[1].getImageView().setX(flagImage.getX());
        brickWalls[1].getImageView().setY(flagImage.getY() - size);
        brickWalls[2] = new BrickWall(2 * canvasWidth / 15, canvasHeight / 20 + 10,
                new ImageView(new Image(
                        "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                        size, true, true)));
        brickWalls[2].getImageView().setX(flagImage.getX() - size);
        brickWalls[2].getImageView().setY(flagImage.getY());
        walls.add(brickWalls[0]);
        walls.add(brickWalls[1]);
        walls.add(brickWalls[2]);
        root.getChildren().addAll(brickWalls[0].getImageView(), brickWalls[1].getImageView(), brickWalls[2].getImageView());

    }

    public ImageView createCopy(ImageView imageView) {
        ImageView copy = new ImageView();
        copy.setX(imageView.getX());
        copy.setY(imageView.getY());
        copy.setImage(imageView.getImage());
        return copy;
    }
}
