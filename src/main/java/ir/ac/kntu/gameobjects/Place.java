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


    public void addBrickToTheTop(Pane root, int size, Group obstaclesGroup, ArrayList<Wall>  walls) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(2 * canvasWidth / 15, canvasHeight / 20 + 10,
                        new ImageView(new Image(
                        "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                        size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j );
                ImageView copy=createCopy(brickWall.getImageView());
                obstaclesGroup.getChildren().add(copy);
                brickWall.drawWall(root);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(11 * canvasWidth / 15, canvasHeight / 20 + 10,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j );
                ImageView copy=createCopy(brickWall.getImageView());
                obstaclesGroup.getChildren().add(copy);
                brickWall.drawWall(root);
            }
        }
        addBrickToTheBot(root, size,obstaclesGroup,walls);
    }

    public void addBrickToTheBot(Pane root, int size,Group obstaclesGroup,ArrayList<Wall> walls) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(2 * canvasWidth / 15, canvasHeight / 20 + 10 + 9 * size,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j );
                ImageView copy=createCopy(brickWall.getImageView());
                obstaclesGroup.getChildren().add(copy);
                brickWall.drawWall(root);
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                BrickWall brickWall = new BrickWall(11 * canvasWidth / 15, canvasHeight / 20 + 10 + 9 * size,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackbrick.png", size,
                                size, true, true)));
                walls.add(brickWall);
                brickWall.getImageView().setX(brickWall.getImageView().getX() + 2.5 * size * i);
                brickWall.getImageView().setY(brickWall.getImageView().getY() + size * j);
                ImageView copy=createCopy(brickWall.getImageView());
                obstaclesGroup.getChildren().add(copy);
                brickWall.drawWall(root);
            }
        }
        addMetalToTheTop(root, size,obstaclesGroup,walls);
    }

    public void addMetalToTheTop(Pane root, int size,Group obstaclesGroup,ArrayList<Wall> walls ) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                MetalWall metalWall = new MetalWall(2 * canvasWidth / 15 + 5 * size, canvasHeight / 20 + 10,
                        new ImageView(new Image(
                                "F:\\project4\\src\\main\\resources\\images\\stackmetal.png", size,
                                size, true, true)));
                walls.add(metalWall);
                metalWall.getImageView().setX(metalWall.getImageView().getX() + 6.5 * size * i);
                metalWall.getImageView().setY(metalWall.getImageView().getY() + size * j );
                ImageView copy=createCopy(metalWall.getImageView());
                obstaclesGroup.getChildren().add(copy);
                metalWall.drawWall(root);

            }
        }

    }

    public ImageView createCopy(ImageView imageView){
        ImageView copy=new ImageView();
        copy.setX(imageView.getX());
        copy.setY(imageView.getY());
        copy.setImage(imageView.getImage());
        return copy;
    }
}
