package ir.ac.kntu;

import ir.ac.kntu.gameObjects.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * @author Sina Rostami
 */
public class Main extends Application {


    private Player player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;");
        Scene scene = new Scene(root, 600, 400);
        ImageView imageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\tank1.png"));
        player = new Player(200, 380, imageView);
        scene.setFill(Color.BLACK);

        root.getChildren().add(player.getImageView());
        player.move(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}