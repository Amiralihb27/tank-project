package ir.ac.kntu.gameobjects;

import ir.ac.kntu.Game;
import ir.ac.kntu.Menu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DefineStages {

    private int currentImageIndex;

    private VBox imageVBox;

    private Scene scene;

    private Stage stage;

    private Game game;

    ImageView arrowKeyImageView;

    public DefineStages(Stage stage, Scene scene, Game game) {
        this.scene = scene;
        this.stage = stage;
        this.game = game;
    }

    public void showStage() {
        ImageView[] imageViews = new ImageView[10];
        Text[] texts = new Text[10];
        HBox[] hbox = new HBox[10];
        hbox[0] = new HBox(5);
        imageVBox = new VBox(10);
        imageVBox.setAlignment(Pos.CENTER);
        arrowKeyImageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "enemyright.png", 40, 40, true, true));
        for (int i = 0; i < 10; i++) {
            imageViews[i] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                    "stage.png", 100, 100, true, true));
            texts[i] = new Text("" + (i));
            texts[i].setFont(Font.font("Arial", 20));
            texts[i].setFill(Color.GRAY);
            if (i == 0) {
                hbox[0].getChildren().addAll(arrowKeyImageView, imageViews[0], texts[0]);
                hbox[0].setAlignment(Pos.CENTER);
                imageVBox.getChildren().add(hbox[0]);
            } else {
                hbox[i] = new HBox(5);
                hbox[i].setAlignment(Pos.CENTER);
                hbox[i].getChildren().addAll(imageViews[i], texts[i]);
                imageVBox.getChildren().add(hbox[i]);
            }
        }
        choosingStage(imageViews, hbox, texts);
    }

    public void choosingStage(ImageView[] imageViews, HBox[] hbox, Text[] texts) {
        currentImageIndex = 0;
        // Set up the event handler to handle arrow key presses
        AnchorPane root = new AnchorPane(imageVBox);
        root.setStyle("-fx-background-color: black;");
        AnchorPane.setLeftAnchor(imageVBox, 70.0);
        AnchorPane.setTopAnchor(imageVBox, 30.0);
        AnchorPane.setRightAnchor(imageVBox, 70.0);
        Scene scene = new Scene(root, 400, 400);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                eventHandling(imageViews, hbox, texts, event);
            }
        });
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public void eventHandling(ImageView[] imageViews, HBox[] hbox, Text[] texts, KeyEvent event) {
        if (event.getCode() == KeyCode.UP && currentImageIndex > 0) {
            currentImageIndex--;
            imageVBox.getChildren().remove(hbox[currentImageIndex]);
            hbox[currentImageIndex] = new HBox(5);
            hbox[currentImageIndex].getChildren().addAll(arrowKeyImageView, imageViews[currentImageIndex],
                    texts[currentImageIndex]);
            hbox[currentImageIndex].setAlignment(Pos.CENTER);
            imageVBox.getChildren().add(currentImageIndex, hbox[currentImageIndex]);
        } else if (event.getCode() == KeyCode.DOWN && currentImageIndex < imageViews.length - 1) {
            currentImageIndex++;
            imageVBox.getChildren().remove(hbox[currentImageIndex]);
            hbox[currentImageIndex] = new HBox(5);
            hbox[currentImageIndex].getChildren().addAll(arrowKeyImageView, imageViews[currentImageIndex],
                    texts[currentImageIndex]);
            hbox[currentImageIndex].setAlignment(Pos.CENTER);
            imageVBox.getChildren().add(currentImageIndex, hbox[currentImageIndex]);
        } else if (event.getCode() == KeyCode.ENTER) {
            game.setNumberOfTotalTanks(currentImageIndex * 4 + 10);
            loading(imageViews[currentImageIndex], texts[currentImageIndex]);
        }
    }


    public void loading(ImageView stage, Text text) {

        ImageView loading = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "loading.png", 200, 200, true, true));
        stage = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "stage.png", 200, 200, true, true));
        Text totalTanks = new Text("Total tanks: " + ((currentImageIndex * 4) + 10));
        totalTanks.setFont(Font.font("Arial", 30));
        totalTanks.setFill(Color.GRAY);
        VBox vBox = new VBox(10);
        text.setFont(Font.font("Arial", 50));
        vBox.getChildren().addAll(loading, stage, text, totalTanks);
        vBox.setAlignment(Pos.CENTER);
        AnchorPane root = new AnchorPane(vBox);
        scene = new Scene(root, 600, 600);
        root.setStyle("-fx-background-color: black;");
        AnchorPane.setLeftAnchor(vBox, scene.getWidth() / 3);
        AnchorPane.setTopAnchor(vBox, scene.getHeight() / 3);
        this.stage.setScene(scene);
        this.stage.show();
        Timeline start = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            game.startGame(this.stage);
        }));
        start.setCycleCount(1);
        start.play();

    }
}
