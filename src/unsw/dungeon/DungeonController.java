package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 */
public class DungeonController {

    @FXML
    private GridPane squares;

    @FXML
    private Label health;

    @FXML
    private Label goals;

    @FXML
    private Button pauseBtn;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    private Image doorOpenImage;

    

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        doorOpenImage = new Image((new File("images/open_door.png")).toURI().toString());
    }

    /**
     * initializes the display for the dungeon
     */
    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        Image pauseImage = new Image((new File("images/pause_icon.png")).toURI().toString());
        // Add the ground first so it is below all other entities
        
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        for (int i = 0; i < initialEntities.size(); i++){
            squares.getChildren().add(initialEntities.get(i));
            trackPosition(dungeon.getEntities().get(i), initialEntities.get(i));
        }
        health.setText("Health: " + player.getHealth().intValue());
        goals.setText("Goals: " + dungeon.getGoals().toString());
        pauseBtn.setLayoutY(0);
        pauseBtn.setLayoutX(250);
        pauseBtn.setGraphic(new ImageView(pauseImage));
        
        trackPlayer(dungeon.getPlayer());
        trackGoals(dungeon.getGoals());
    }

    /**
     * User input for the dungeon game
     * @param event
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case C:
            player.usePotion();
            break;
        default:
            break;
        }
    }

    /**
     * To the pause screen
     * @throws Exception
     */
    //@FXML
    public void handlePause(ActionEvent event) throws Exception{

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        //dialog.initOwner(primaryStage);
        Button resume = new Button();
        Button restart = new Button();
        Button exit = new Button();
        Button mainMenu = new Button();

        resume.setText("resume");
        resume.setStyle("-fx-background-color: pink");
        resume.setCursor(Cursor.CROSSHAIR);
        resume.setEffect(new Lighting());
        restart.setText("restart");
        restart.setStyle("-fx-background-color: pink");
        restart.setEffect(new Lighting());
        exit.setText("exit");
        exit.setStyle("-fx-background-color: orange");
        exit.setEffect(new Lighting());
        mainMenu.setText("Main menu");
        mainMenu.setStyle("-fx-background-color: orange");
        mainMenu.setEffect(new Lighting());

        resume.setOnAction(e -> {
            dialog.close();
            squares.requestFocus();
        });
        restart.setOnAction(e -> {
            dialog.close();
            squares.requestFocus();
        });
        exit.setOnAction(e -> {
            dialog.close();
            Stage curr = (Stage)squares.getScene().getWindow();
            curr.close();
        });
        mainMenu.setOnAction(e-> {
            dialog.close();
            Stage curr = (Stage)squares.getScene().getWindow();
            curr.close();
            DungeonApplication hmm = new DungeonApplication();
            Stage primaryStage= new Stage();
            try {
                hmm.start(primaryStage);
            } catch (Exception ex) {
                System.err.println("Error:" + ex.toString());
            }
        });

        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-color: grey");
        Label heading = new Label("Game Paused");
        heading.setStyle("-fx-font: 21 arial;"+"-fx-font-weight: bold;");
        //heading.setStyle("-fx-font-weight: bold;");
        dialogVbox.getChildren().add(heading);
        dialogVbox.getChildren().addAll(resume, restart, mainMenu, exit);
        dialogVbox.setAlignment(Pos.CENTER);
        Scene dialogScene = new Scene(dialogVbox, 200, 220);
        dialog.setTitle("Pause Menu");
        dialog.setScene(dialogScene);
        dialog.show();

    }
    /**
     * Tracks the players health through an observer Pattern
     * If the players health reaches 0 then swich to the loosing screen
     * @param entity the player that is to be tracked
     */
    private void trackPlayer(Player entity){
        entity.getHealth().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, 
                    Number oldValue, Number newValue){
                health.setText("Health: " + newValue.intValue());
                if(newValue.intValue() == 0){
                    
                    try {
                        endLose();
                    } catch (Exception e) {
                        System.err.println("Error:" + e.toString());
                    }
                }
            }
        });

    }

    /**
     * Tracks the goal conditions of the game through an observer pattern
     * @param goal the goal that is to be tracked
     */
    private void trackGoals(Goals goal){
        goal.complete().addListener(new ChangeListener<Boolean>(){
            @Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
                    try {
                        endWin();
                    } catch (Exception e) {
                        System.err.println("Error:" + e.toString());
                    }
                }
			}
        });
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(newValue.intValue() == -1){
                    squares.getChildren().remove(node);
                } else {
                    GridPane.setColumnIndex(node, newValue.intValue());
                }
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(newValue.intValue() == -1){
                    squares.getChildren().remove(node);
                } else {
                    GridPane.setRowIndex(node, newValue.intValue());
                }
                
            }
        });
        if(entity.getClass().equals(new Door(0, 0 , 1).getClass())){
            Door d = (Door) entity;
            d.getOpen().addListener(new ChangeListener<Boolean>(){
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0, 
                        Boolean oldValue, Boolean newValue) {
                    if(newValue){
                        GridPane.setRowIndex(new ImageView(doorOpenImage), d.getX());
                    }
                }
            });
        }
    }

    /**
     * To the lose game end screen
     * @throws Exception
     */
    private void endLose() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("end_lose.fxml"));
        EndController end = new EndController();
        loader.setController(end);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) squares.getScene().getWindow();
        window.setScene(scene);
    }

    /**
     * To the win game end screen
     * @throws Exception
     */
    private void endWin() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("end_win.fxml"));
        EndController end = new EndController();
        loader.setController(end);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) squares.getScene().getWindow();
        window.setScene(scene);
    }
    
    

}
