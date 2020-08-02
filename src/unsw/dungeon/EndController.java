package unsw.dungeon;

import java.io.IOError;
import java.io.IOException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EndController{
    @FXML
    private AnchorPane root;

    @FXML
    private Button exit;

    @FXML
    private Button first;

    @FXML
    private Label output;


    public EndController(){
    }

    /**
     * initializes the end controller to display one of the end screens
     */
    @FXML
    public void initialize(){
        //System.out.println(text);

        root.setOpacity(0);
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

    /**
     * 
     * @param event Depending on the screen, if the user has won change scene to next level, else restart the level
     */
    @FXML
    public void handleButtonPressFirst(ActionEvent event) throws IOException{
        if(first.getText().equals("LEVEL SELECT")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Level_select.fxml"));
            LevelSelectController select = new LevelSelectController();
            loader.setController(select);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage window = (Stage) exit.getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            //TODO send restart the user on the current level
        }
    }

    /**
     * 
     * @param event Exit the game
     */
    @FXML
    public void handleButtonPressExit(ActionEvent event){
        Stage currStage = (Stage) root.getScene().getWindow();
        currStage.close();
    }
}