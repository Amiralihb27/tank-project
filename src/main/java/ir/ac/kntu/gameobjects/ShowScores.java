package ir.ac.kntu.gameobjects;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        ImageView[] score = new ImageView[3];
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
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox);
        vBox.getChildren().add(line);
        vBox.getChildren().add(text[3]);
        vBox.setAlignment(Pos.CENTER);

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
}
