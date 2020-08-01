package unsw.dungeon;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PauseController {
    @FXML
    private AnchorPane root;

    @FXML
    private Label displayMsg;
    @FXML
    private Button resumeBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Button restartLvlBtn;



    public PauseController(){
    }

    /**
     * initializes the end controller to display one of the end screens
     */
    @FXML
    public void initialize(){
        //System.out.println(text);
        

        root.setOpacity(1);
        fadeInTransition();
    }

    private void fadeInTransition(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    @FXML
    public void handleResumeBtn(ActionEvent evene){
        
    }
    /**
     * 
     * @param event Depending on the screen, if the user has won change scene to next level, else restart the level
     */
    @FXML
    public void handleRestartLvlBtn(ActionEvent event){
        Stage currStage = (Stage) root.getScene().getWindow();
        currStage.close();
    }

    /**
     * 
     * @param event Exit the game
     */
    @FXML
    public void handleExitBtn(ActionEvent event){
        Stage currStage = (Stage) root.getScene().getWindow();
        currStage.close();
    }
}