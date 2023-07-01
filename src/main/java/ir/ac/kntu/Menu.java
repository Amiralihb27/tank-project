package ir.ac.kntu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.StrokeType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    private Stage stage;

    private Scene scene;

    private VBox menuBox;

    private HBox hbox;

    private ImageView[] menuItems;

    private int currentItem;


    private Pane root;


    public Stage getStage() {
        return stage;
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public Scene getScene() {
        return scene;
    }


    public void setScene(Scene scene) {
        this.scene = scene;
    }


    public VBox getMenuBox() {
        return menuBox;
    }

    public void setMenuBox(VBox menuBox) {
        this.menuBox = menuBox;
    }


    public int getCurrentItem() {
        return currentItem;
    }


    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
    }

    public Menu(Stage stage) {
        this.stage = stage;
        this.menuItems = new ImageView[4]; // Number of menu items

        // Create menu items
        menuItems[0] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "gamename.png", 500, 500, true, true));
        menuItems[1] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "1player.png", 200, 200, true, true));
        menuItems[2] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\" +
                "2player.png", 200, 200, true, true));

        menuItems[3] = new ImageView(new Image("F:\\project4\\src\\main\\resources\\images\\tank4.png", 50, 50,
                 true, true));


        // Set initial current item
        currentItem = 0;

        // Create the menu layout
        System.out.println(menuItems[0].getLayoutY());
        menuBox = new VBox();
        menuBox.setSpacing(10);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.getChildren().add(menuItems[0]);
        System.out.println(menuItems[0].getLayoutY());
        hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(menuItems[3]);
        hbox.getChildren().add(menuItems[1]);

        menuBox.getChildren().add(hbox);
        menuBox.getChildren().add(menuItems[2]);
        //  menuBox.getChildren().add(pointer);
        System.out.println(menuItems[0].getLayoutY());
        // Create the scene
        scene = new Scene(menuBox, 600, 600);
        System.out.println(menuItems[0].getLayoutY());
        scene.setFill(Color.BLACK);
        System.out.println(scene.getFill());

        // scene.setOnKeyPressed(this::handleKeyPress); // Add key press event listener to the scene
    }

    public void show() {
        // scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public void handleKeyPress(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            // User pressed Enter, move to the next scene (start game menu)
            if (currentItem <= 0) {
                createSignInScene();
                // startGameMenu();
            }

        } else if (event.getCode() == KeyCode.UP && currentItem > 0) {
            // User pressed Up arrow, move selection up
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(menuItems[3]);
            hbox.getChildren().add(menuItems[1]);
            menuBox.getChildren().setAll(menuItems[0], hbox, menuItems[2]);
            currentItem--;
        } else if (event.getCode() == KeyCode.DOWN && currentItem < menuItems.length - 3) {
            // User pressed Down arrow, move selection down
            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().add(menuItems[3]);
            hbox.getChildren().add(menuItems[2]);
            menuBox.getChildren().setAll(menuItems[0], menuItems[1], hbox);
            currentItem++;
        }
    }


    public void startGameMenu() {
        // Redirect to the game class and its start menu
        Main game = new Main(); // Assuming there is a Game class
        game.startGame(stage);
    }

    public void createSignInScene() {
        // Create sign-in elements (text fields, button, etc.)
        ArrayList<String> users = new ArrayList<>();
        handlingUsers(users);
        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button signInButton = new Button("Sign In");
        colorizeButtons(usernameTextField, passwordField, usernameLabel, passwordLabel);
        // Validate credentials on button click
        signInHandling(signInButton, usernameTextField, passwordField, users);
        // Arrange the elements in a VBox or any other suitable container
        VBox signInLayout = new VBox(10);
        signInLayout.setStyle("-fx-background-color: black;");
        signInLayout.setPadding(new Insets(15));
        signInLayout.setAlignment(Pos.CENTER);
        signInLayout.getChildren().addAll(usernameLabel, usernameTextField, passwordLabel, passwordField, signInButton);
        // Create the sign-in scene
        scene = new Scene(signInLayout, 400, 400);

        scene.getStylesheets().add(getClass().getResource("java/ir/ac/kntu/style.css").toExternalForm());
        scene.setFill(Color.BLACK);
        stage.setTitle("Sign In");
        stage.setScene(scene);
    }

    public void colorizeButtons(TextField usernameTextField, TextField passwordField, Label usernameLabel,
             Label passwordLabel) {
        usernameLabel.getStyleClass().add("label-class");
        passwordLabel.getStyleClass().add("label-class");
        passwordField.getStyleClass().add("textfield-class");
        usernameTextField.getStyleClass().add("textfield-class");
        usernameTextField.setMaxWidth(300);
        usernameTextField.setPrefWidth(300);
        passwordField.setMaxWidth(300);
        passwordField.setPrefWidth(300);
        System.out.println(scene.getFill());
    }

    public void signInHandling(Button signInButton, TextField usernameTextField, TextField passwordField,
                               ArrayList<String> users) {
        signInButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            if (validateCredentials(username, password, users)) {
                startGameMenu();
            } else {
                showInvalidCredentialsMessage();
            }
        });
    }

    public void handlingUsers(ArrayList<String> users) {
        try (BufferedReader fileName = new BufferedReader(new FileReader("Users.txt"))) {
            String line;
            while ((line = fileName.readLine()) != null) {
                users.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateCredentials(String username, String password, ArrayList<String> users) {
        for (int i = 0; i < users.size(); i++) {
            String[] information = users.get(i).split(" ");
            if (information[0].equals(username) && information[1].equals(password)) {
                return true;
            }
        }
        return false;
    }


    public void showInvalidCredentialsMessage() {
        // Show a message indicating invalid credentials
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Credentials");
        alert.setHeaderText(null);
        alert.setContentText("Invalid username or password. Please try again.");
        alert.showAndWait();
    }


}
