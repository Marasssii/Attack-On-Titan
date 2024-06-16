package game.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import game.engine.Battle;
import game.engine.titans.Titan;

public class Main extends Application {
    private Scene startScreen, eGameScreen, hGameScreen;
    private VBox box1, box2, box3;
    private Label DL1 , DL2, DL3;
    private Label HP1 , HP2, HP3;
    private Button PS;
    private Battle battle;
    private TextArea gameDS;
    VBox[] lanesTitans;
    
    @Override
    public void start(Stage primaryStage) {
        // Start screen
        AnchorPane startRoot = createStartScreen(primaryStage);
        startScreen = new Scene(startRoot, 600, 400);

        // Easy game screen
        AnchorPane eGameRoot = createEasyGameScreen(primaryStage);
        eGameScreen = new Scene(eGameRoot, 600, 400);

        // Hard game screen
        AnchorPane hGameRoot = createHardGameScreen(primaryStage);
        hGameScreen = new Scene(hGameRoot, 600, 400);

        primaryStage.setScene(startScreen);
        primaryStage.setTitle("Attack on Titan");
        primaryStage.setMaximized(false);
        primaryStage.show();
    }

    private AnchorPane createStartScreen(Stage stage) {
        AnchorPane pane = new AnchorPane();

        Button easyButton = new Button("Easy");
        easyButton.setLayoutX(148);
        easyButton.setLayoutY(216);
        easyButton.setOnAction(e -> stage.setScene(eGameScreen));
 

        Button hardButton = new Button("Hard");
        hardButton.setLayoutX(398);
        hardButton.setLayoutY(216);
        hardButton.setOnAction(e -> stage.setScene(eGameScreen));

        Label title = new Label("Attack On Titan");
        title.setLayoutX(277);
        title.setLayoutY(46);

        Label chooseDifficulty = new Label("Choose Your Difficulty");
        chooseDifficulty.setLayoutX(277);
        chooseDifficulty.setLayoutY(122);
        
        TextArea gameDS = new TextArea("The main objective is to survive as many turns as possible by preventing Titans from reaching and destroying the fortress."
        + " Players score points by eliminating Titans, and they must manage resources effectively to purchase and upgrade weapons.");
        gameDS.setLayoutX(0);
        gameDS.setLayoutY(300);
        gameDS.setPrefWidth(600);

        pane.getChildren().addAll(easyButton, hardButton, title, chooseDifficulty,gameDS);
        return pane;
    }

    private AnchorPane createEasyGameScreen(Stage stage) {
        AnchorPane pane = new AnchorPane();

        Rectangle rect1 = new Rectangle(600, 102);
        rect1.setArcHeight(5.0);
        rect1.setArcWidth(5.0);
        rect1.setFill(javafx.scene.paint.Color.web("#ff301f"));
        rect1.setStroke(javafx.scene.paint.Color.BLACK);

        Rectangle rect2 = new Rectangle(600, 102);
        rect2.setArcHeight(5.0);
        rect2.setArcWidth(5.0);
        rect2.setFill(javafx.scene.paint.Color.web("#ff341f"));
        rect2.setStroke(javafx.scene.paint.Color.BLACK);
        rect2.setLayoutY(102);

        Rectangle rect3 = new Rectangle(600, 102);
        rect3.setArcHeight(5.0);
        rect3.setArcWidth(5.0);
        rect3.setFill(javafx.scene.paint.Color.web("#ff341f"));
        rect3.setStroke(javafx.scene.paint.Color.BLACK);
        rect3.setLayoutY(204);

        Button piercingCannonButton = new Button("PiercingCannon 25 10 Anti-TitanShell");
        piercingCannonButton.setLayoutY(306);
        piercingCannonButton.setOnAction(e -> handleEasyGameAction("PiercingCannon"));

        Button sniperCannonButton = new Button("SniperCannon 25 35 Long Range Spear");
        sniperCannonButton.setLayoutY(328);
        sniperCannonButton.setOnAction(e -> handleEasyGameAction("SniperCannon"));

        Button volleySpreadCannonButton = new Button("Volley SpreadCannon 100 5 Wall SpreadCannon 20 50");
        volleySpreadCannonButton.setLayoutY(353);
        volleySpreadCannonButton.setOnAction(e -> handleEasyGameAction("VolleySpreadCannon"));

        Button wallTrapButton = new Button("Wall Trap 75 100 Proximity Trap");
        wallTrapButton.setLayoutY(374);
        wallTrapButton.setOnAction(e -> handleEasyGameAction("WallTrap"));
        
        
         try {
			battle = new Battle (1,0,100,3,250);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Label currentScore = new Label("Current score:" +battle.getScore());
        currentScore.setLayoutX(374);
        currentScore.setLayoutY(310);

        Label currentTurn = new Label("Current turn:" + battle.getNumberOfTurns());
        currentTurn.setLayoutX(483);
        currentTurn.setLayoutY(310);

        Label currentPhase = new Label("Current phase:" + battle.getBattlePhase());
        currentPhase.setLayoutX(373);
        currentPhase.setLayoutY(332);

        Label currentResources = new Label("Current Resources:" + battle.getResourcesGathered());
        currentResources.setLayoutX(483);
        currentResources.setLayoutY(333);

        Label availableLanes = new Label("Available lanes:");
        availableLanes.setLayoutX(422);
        availableLanes.setLayoutY(357);

        Line line1 = new Line(93, 102, 462, 102);
        Line line2 = new Line(93, 205, 462, 205);
        Line line3 = new Line(30, 306, 470, 306);

        box1 = new VBox();
        box1.setLayoutX(500);
        box1.setPrefHeight(103);
        box1.setPrefWidth(57);

        box2 = new VBox();
        box2.setLayoutX(498);
        box2.setLayoutY(100);
        box2.setPrefHeight(103);
        box2.setPrefWidth(57);

        box3 = new VBox();
        box3.setLayoutX(500);
        box3.setLayoutY(201);
        box3.setPrefHeight(103);
        box3.setPrefWidth(57);

        DL1 = new Label("DL:");
        DL1.setPrefWidth(30);
        DL1.setPrefHeight(30);
        DL1.setLayoutX(550);
        DL1.setLayoutY(25);
        
        DL2 = new Label("DL:");
        DL2.setPrefWidth(30);
        DL2.setPrefHeight(30);
        DL2.setLayoutX(550);
        DL2.setLayoutY(125);
        
        DL3 = new Label("DL:");
        DL3.setPrefWidth(30);
        DL3.setPrefHeight(30);
        DL3.setLayoutX(550);
        DL3.setLayoutY(225);
        
        HP1 = new Label("HP:");
        HP1.setPrefWidth(30);
        HP1.setPrefHeight(30);
        HP1.setLayoutX(550);
        HP1.setLayoutY(50);
        
        HP2 = new Label("HP:");
        HP2.setPrefWidth(30);
        HP2.setPrefHeight(30);
        HP2.setLayoutX(550);
        HP2.setLayoutY(150);

        HP3 = new Label("HP:");
        HP3.setPrefWidth(30);
        HP3.setPrefHeight(30);
        HP3.setLayoutX(550);
        HP3.setLayoutY(250);
        VBox[] lanesTitans = new VBox[]{box1, box2, box3};
        
        PS = new Button("Pass Turn:");
        PS.setLayoutX(270);
        PS.setLayoutY(320);
        PS.setPrefHeight(20);
        PS.setPrefWidth(80);
        PS.setOnAction(e -> {
            battle.passTurn();
            currentScore.setText("Current score:" +battle.getScore());
        	currentTurn.setText("Current turn:" + battle.getNumberOfTurns());
        	currentPhase.setText("Current phase:" + battle.getBattlePhase());
        	currentResources.setText("Current Resources:" + battle.getResourcesGathered());
        	availableLanes.setText("availableLanes:" +  battle.getLanes());
        	currentScore.setText("currentScore:" + battle.getScore());
        	

        	
        });
        
        

        
        pane.getChildren().addAll(rect1, rect2, rect3, piercingCannonButton, sniperCannonButton, volleySpreadCannonButton, wallTrapButton,
                currentScore, currentTurn, currentPhase, currentResources, availableLanes, line1, line2, line3, box1, box2, box3,DL1,DL2,DL3,HP1,HP2,HP3,PS);
        return pane;
    }

  

	private AnchorPane createHardGameScreen(Stage stage) {
        AnchorPane pane = new AnchorPane();
        Label hardGameLabel = new Label("Hard Game Screen");
        hardGameLabel.setLayoutX(250);
        hardGameLabel.setLayoutY(200);
        pane.getChildren().add(hardGameLabel);
        return pane;
    }

    private void handleEasyGameAction(String action) {
        System.out.println(action + " button pressed");
        
       

        
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Select Lane");
        dialog.setHeaderText("Choose a lane for " + action);
        dialog.setContentText("Enter lane number (1, 2, or 3):");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(lane -> {
            try {
                int laneNumber = Integer.parseInt(lane);
                placeWeaponInLane(action, laneNumber);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR, "Invalid lane number. Please enter 1, 2, or 3.");
                alert.showAndWait();
            }
        });
    }

    private void placeWeaponInLane(String action, int laneNumber) {
        String imagePath = "";

        switch (action) {
            case "PiercingCannon":
                imagePath = "PiercingCannon.jpg";
                break;
            case "SniperCannon":
                imagePath = "SniperCannon.jpg";
                break;
            case "VolleySpreadCannon":
                imagePath = "VolleySpreadCannon.jpg";
                break;
            case "WallTrap":
                imagePath = "WallTrap.jpg";
                break;
            default:
                Alert alert = new Alert(AlertType.ERROR, "Invalid weapon type.");
                alert.showAndWait();
                return;
        }

        Image weaponImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(weaponImage);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        switch (laneNumber) {
            case 1:
                box1.getChildren().add(imageView);
                break;
            case 2:
                box2.getChildren().add(imageView);
                break;
            case 3:
                box3.getChildren().add(imageView);
                break;
            default:
                Alert alert = new Alert(AlertType.ERROR, "Invalid lane number. Please enter 1, 2, or 3.");
                alert.showAndWait();
        }
    }
   
 
    public static void main(String[] args) {
        launch(args);
    }
}
       