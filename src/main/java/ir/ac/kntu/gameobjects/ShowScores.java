package ir.ac.kntu.gameobjects;

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
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ShowScores {

    private Stage stage;

    private Scene scene;

    private int currentImageIndex;

    private VBox imageVBox;

    private Player player;

    private User user;

    public ShowScores(Stage stage, Scene scene, Player player, User user) {
        this.scene = scene;
        this.stage = stage;
        this.user = user;
        this.player = player;

    }


    public void showScreen() {
        HBox[] hBox = new HBox[4];
        hBox[0] = new HBox();
        hBox[0].setSpacing(10);
        ImageView highScore = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\hiscore.png"
                , 200, 200, true, true));
        Text[] text = new Text[5];
        text[0] = new Text("" + user.getHighScore());
        text[0].setFont(Font.font("Arial", 20));
        text[0].setFill(Color.GOLD);
        hBox[0].getChildren().addAll(highScore, text[0]);
        hBox[1] = new HBox(20);
        text[1] = new Text("Count : " + player.shieldTanks().size() + "        Score : "
                + player.shieldTanks().size() * 200);
        text[1].setFont(Font.font("Arial", 20));
        text[1].setFill(Color.GOLD);
        ImageView[] score = new ImageView[4];
        score[0] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemyupmetal.png"
                , 50, 50, true, true));
        hBox[1].getChildren().addAll(score[0], text[1]);
        score[1] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemytank1.png"
                , 50, 50, true, true));
        text[2] = new Text("Count : " + player.ordinaryTanks().size() + "        Score : "
                + player.ordinaryTanks().size() * 100);
        text[2].setFont(Font.font("Arial", 20));
        text[2].setFill(Color.GOLD);
        hBox[2] = new HBox(20);
        hBox[2].getChildren().addAll(score[1], text[2]);
        Line line = new Line();
        line.setStartX(50);
        line.setStartY(50);
        line.setEndX(250);
        line.setEndY(50);

        // Set the color of the line
        line.setStroke(Color.RED); // Replace with your desired color
        text[3] = new Text("Total Score: " + (player.ordinaryTanks().size() * 100 + player.shieldTanks().size() * 200));
        text[3].setFont(Font.font("Arial", 40));
        text[3].setFill(Color.GOLD);
        // Add the line to the Pane
        hBox[0].setAlignment(Pos.CENTER);
        hBox[1].setAlignment(Pos.CENTER);
        hBox[2].setAlignment(Pos.CENTER);

        score[2] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\Total.png"
                , 100, 100, true, true));

        text[4] = new Text("" + (player.ordinaryTanks().size() + player.shieldTanks().size()));
        text[4].setFont(Font.font("Arial", 40));
        text[4].setFill(Color.GOLD);
        hBox[3] = new HBox(10);
        hBox[3].getChildren().addAll(score[2], text[4]);
        hBox[3].setAlignment(Pos.CENTER);
        score[3] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\gameover.png"
                , 100, 100, true, true));
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox);
        vBox.getChildren().add(line);
        vBox.getChildren().add(text[3]);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(score[3]);

        AnchorPane root = new AnchorPane();
        root.getChildren().add(vBox);
        AnchorPane.setTopAnchor(vBox, 70.0);
        AnchorPane.setLeftAnchor(vBox, 70.0);
        AnchorPane.setRightAnchor(vBox, 70.0);
        root.setStyle("-fx-background-color: black;");
        scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();


    }

    public void showStage() {
        ImageView[] imageViews = new ImageView[10];
        Text[] texts = new Text[10];
        HBox[] hbox = new HBox[10];
        hbox[0] = new HBox(5);
         imageVBox = new VBox();
        imageVBox.setAlignment(Pos.CENTER);
        ImageView arrowKeyImageView = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "enemyright.png"));

        for (int i = 0; i < 10; i++) {
            imageViews[i] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                    "stage.png", 50, 50, true, true));
            texts[i] = new Text("" + (i + 1));
            texts[i].setFont(Font.font("Arial", 20));
            texts[i].setFill(Color.GRAY);
            if (i == 0) {
                hbox[0].getChildren().addAll(arrowKeyImageView, imageViews[0], texts[0]);
                hbox[0].setAlignment(Pos.CENTER);
                imageVBox.getChildren().add(hbox[0]);
            } else {
                hbox[i] = new HBox(5);
                hbox[i].getChildren().addAll(imageViews[i], texts[i]);
                imageVBox.getChildren().add(hbox[i]);
            }

        }
    }

    public void choosingStage(ImageView[] imageViews,HBox[] hbox,ImageView arrowKeyImageView,Text[] texts) {
         currentImageIndex = 0;

        // Set up the event handler to handle arrow key presses
        Scene scene = new Scene(imageVBox, 400, 400);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP && currentImageIndex > 0) {
                    // Move arrow key image up
                    currentImageIndex--;
                    imageVBox.getChildren().remove( hbox[currentImageIndex]);
                     hbox[currentImageIndex] = new HBox(5);
                    hbox[currentImageIndex].getChildren().addAll(arrowKeyImageView, imageViews[currentImageIndex]);
                    hbox[currentImageIndex].setAlignment(Pos.CENTER);
//                    imageVBox.getChildren().remove(arrowKeyImageView);
//                    imageVBox.getChildren().remove(imageViews[currentImageIndex]);
                    imageVBox.getChildren().add(currentImageIndex, hbox[currentImageIndex]);
                } else if (event.getCode() == KeyCode.DOWN && currentImageIndex < imageViews.length - 1) {
                    // Move arrow key image down
                    currentImageIndex++;
                    imageVBox.getChildren().remove( hbox[currentImageIndex]);
                    hbox[currentImageIndex] = new HBox(5);
                    hbox[currentImageIndex].getChildren().addAll(arrowKeyImageView, imageViews[currentImageIndex]);
                    hbox[currentImageIndex].setAlignment(Pos.CENTER);
//                    imageVBox.getChildren().remove(arrowKeyImageView);
//                    imageVBox.getChildren().remove(imageViews[currentImageIndex]);
                    imageVBox.getChildren().add(currentImageIndex, hbox[currentImageIndex]);
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }
}
