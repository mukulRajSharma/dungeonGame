package unsw.dungeon;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    private String text;

    public EndController(String text){
        this.text = text;
    }

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

    @FXML
    public void handleButtonPressFirst(ActionEvent event){
        if(first.getText() == "NEXTLEVEL"){
            //TODO send the user to the next level
        } else {
            //TODO send restart the user on the current level
        }
    }

    @FXML
    public void handleButtonPressExit(ActionEvent event){
        Stage currStage = (Stage) root.getScene().getWindow();
        currStage.close();
    }
}