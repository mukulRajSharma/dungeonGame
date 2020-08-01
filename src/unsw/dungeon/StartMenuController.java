package unsw.dungeon;

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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class StartMenuController {
    @FXML
    private AnchorPane root;

    @FXML
    private Button easyBtn;

    @FXML
    private Button mediumBtn;

    @FXML
    private Button hardBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Button helpBtn;

    @FXML
    private Label startLabel;


    /**
     * initializes the end controller to display one of the end screens
     */
    @FXML
    public void initialize() {
        // System.out.println(text);

        root.setOpacity(1);
        fadeInTransition();
    }

    private void fadeInTransition() {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(root);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    private FXMLLoader levelLoader(String dungeonName) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeonName);
        DungeonController controller = dungeonLoader.loadController();
        FXMLLoader load = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        load.setController(controller);
        return load;
    }
    /**
     * 
     * @param event Depending on the screen, if the user has won change scene to
     *              next level, else restart the level
     */
    @FXML
    public void handleEasyBtn(ActionEvent event) throws Exception {
        Stage boss = (Stage)root.getScene().getWindow();
        boss.close();
        Stage window = new Stage();
        FXMLLoader loader = levelLoader("advanced.json");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleMediumBtn(ActionEvent event) throws Exception{
        Stage boss = (Stage)root.getScene().getWindow();
        boss.close();
        Stage window = new Stage();
        FXMLLoader loader = levelLoader("advanced.json");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleHardBtn(ActionEvent event) throws Exception{
        Stage boss = (Stage)root.getScene().getWindow();
        boss.close();
        Stage window = new Stage();
        FXMLLoader loader = levelLoader("advanced.json");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleExitBtn(ActionEvent event) throws Exception{
        Stage boss = (Stage)root.getScene().getWindow();
        boss.close();
    }

    @FXML
    public void handleHelpBtn(ActionEvent event) throws Exception{
        Stage boss = (Stage)root.getScene().getWindow();
        boss.close();
        Stage window = new Stage();
        FXMLLoader loader = levelLoader("advanced.json");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window.setScene(scene);
        window.show();
    }
}