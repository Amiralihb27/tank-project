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

    private HBox[] hBox;

    private Text[] text;

    private ImageView[] score;

    private Player player;

    private User user;

    private boolean isWinning = false;

    public ShowScores(Stage stage, Scene scene, Player player, User user) {
        this.scene = scene;
        this.stage = stage;
        this.user = user;
        this.player = player;

    }


    public boolean isWinning() {
        return isWinning;
    }

    public void setWinning(boolean winning) {
        isWinning = winning;
    }

    public void showScreen() {
        hBox = new HBox[6];
        hBox[0] = new HBox();
        hBox[0].setSpacing(10);
        ImageView highScore = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\hiscore.png",
                200, 200, true, true));
        text = new Text[7];
        text[0] = new Text("" + user.getHighScore());
        text[0].setFont(Font.font("Arial", 20));
        text[0].setFill(Color.GOLD);
        hBox[0].getChildren().addAll(highScore, text[0]);
        hBox[1] = new HBox(20);
        text[1] = new Text("Count : " + player.shieldTanks().size() + "        Score : "
                + player.shieldTanks().size() * 200);
        text[1].setFont(Font.font("Arial", 20));
        text[1].setFill(Color.GOLD);
        score = new ImageView[6];
        score[0] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemyupmetal.png",
                50, 50, true, true));
        hBox[1].getChildren().addAll(score[0], text[1]);
        score[1] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemytank1.png",
                50, 50, true, true));
        text[2] = new Text("Count : " + player.ordinaryTanks().size() + "        Score : "
                + player.ordinaryTanks().size() * 100);
        text[2].setFont(Font.font("Arial", 20));
        text[2].setFill(Color.GOLD);
        hBox[2] = new HBox(20);
        hBox[2].getChildren().addAll(score[1], text[2]);
        addRandomanks();
    }

    public void addRandomanks() {
        score[2] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemyrandomup.png",
                45, 45, true, true));
        text[3] = new Text("Count : " + player.ordinaryRandom().size() + "        Score : "
                + player.ordinaryRandom().size() * 100);
        text[3].setFont(Font.font("Arial", 20));
        text[3].setFill(Color.GOLD);
        hBox[3] = new HBox(20);
        hBox[3].getChildren().addAll(score[2], text[3]);
        score[3] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\enemyrandommetalup.png",
                45, 45, true, true));
        text[4] = new Text("Count : " + player.shieldRandom().size() + "        Score : "
                + player.shieldRandom().size() * 200);
        text[4].setFont(Font.font("Arial", 20));
        text[4].setFill(Color.GOLD);
        hBox[4] = new HBox(20);
        hBox[4].getChildren().addAll(score[3], text[4]);
        afterLine();
    }

    public void afterLine() {
        Line line = new Line(50, 50, 250, 50);
        line.setStroke(Color.RED); // Replace with your desired color
        text[5] = new Text("Total Score: " + (player.ordinaryTanks().size() * 100 + player.shieldTanks().size() * 200
                + player.shieldRandom().size() * 200 + player.ordinaryRandom().size() * 100));
        text[5].setFont(Font.font("Arial", 40));
        text[5].setFill(Color.GOLD);
        hBox[0].setAlignment(Pos.CENTER);
        hBox[1].setAlignment(Pos.CENTER);
        hBox[2].setAlignment(Pos.CENTER);
        score[4] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\Total.png",
                100, 100, true, true));
        text[6] = new Text("" + (player.ordinaryTanks().size() + player.shieldTanks().size() +
                player.shieldRandom().size() + player.ordinaryRandom().size()));
        text[6].setFont(Font.font("Arial", 40));
        text[6].setFill(Color.GOLD);
        hBox[5] = new HBox(10);
        hBox[5].getChildren().addAll(score[4], text[6]);
        hBox[5].setAlignment(Pos.CENTER);
        if (isWinning == false) {
            score[5] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\gameover.png",
                    100, 100, true, true));
        } else {
            score[5] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\winning.jpg",
                    200, 200, true, true));
        }
        addToScene(line);
    }

    public void addToScene(Line line) {
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox);
        vBox.getChildren().add(line);
        vBox.getChildren().add(text[5]);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(score[5]);
        AnchorPane root = new AnchorPane();
        root.getChildren().add(vBox);
        AnchorPane.setTopAnchor(vBox, 20.0);
        AnchorPane.setLeftAnchor(vBox, 70.0);
        //  AnchorPane.setRightAnchor(vBox, 70.0);
        root.setStyle("-fx-background-color: black;");
        scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    }

}
