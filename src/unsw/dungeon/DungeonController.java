package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());

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
        trackHealth(dungeon.getPlayer());
        trackGoals(dungeon.getGoals());
    }

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
            //player.collectItem();
            break;
        default:
            break;
        }
    }

    private void trackHealth(Player entity){
        entity.getHealth().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observable, 
                    Number oldValue, Number newValue){
                health.setText("Health: " + newValue.intValue());
                if(newValue.intValue() == 0){
                    
                    try {
                        //Should take the user to the loosing screen
                        endLose();
                    } catch (Exception e) {
                        System.err.println("Error:" + e.toString());
                    }
                }
            }
        });
    }

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
    }

    private void endLose() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("end_lose.fxml"));
        EndController end = new EndController("RESTART LEVEL");
        loader.setController(end);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) squares.getScene().getWindow();
        window.setScene(scene);
    }

    private void endWin() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("end_win.fxml"));
        EndController end = new EndController("NEXT LEVEL");
        loader.setController(end);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) squares.getScene().getWindow();
        window.setScene(scene);
    }
    
    

}

