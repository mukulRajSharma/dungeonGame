package unsw.dungeon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.FailedLoginException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelSelectController {

    @FXML
    private Pane root;

    @FXML
    private GridPane grid;

    @FXML
    private List<ImageView> initialLevels;

    private List<Button> levelButtons;


    public LevelSelectController(){
        initialLevels = new ArrayList<>();
        levelButtons = new ArrayList<>();
    }

    @FXML
    public void initialize() throws FileNotFoundException {

        grid = new GridPane();
        root.getChildren().add(grid);

        grid.setHgap(10);
        grid.setVgap(10);

        root.setOpacity(0);
        fadeInTransition();

        String[] pathNames;
        File f = new File("examples");

        FilenameFilter filter = new FilenameFilter(){
            @Override
            public boolean accept(File f, String name){
                return name.endsWith(".png");
            }
        };

        pathNames = f.list(filter);
        
        for(String s: pathNames){
            FileInputStream input = new FileInputStream("examples/" + s);
            Image nImage = new Image(input);
            ImageView view = new ImageView(nImage);
            view.setFitHeight(200);
            view.setFitWidth(200);
            initialLevels.add(view);
            levelButtons.add(new Button(s.replace(".png","")));
        }

        for(int i = 0; i < initialLevels.size(); i++){
            grid.add(initialLevels.get(i),i,0);
            grid.add(levelButtons.get(i),i,1);
            levelButtons.get(i).addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    Button b = (Button)event.getSource();
                    Stage window = (Stage)grid.getScene().getWindow();
                    FXMLLoader loader;
                    try {
                        loader = levelLoader(b.getText() + ".fxml");
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                    window.setScene(scene);
                    } catch (Exception e) {
                        System.err.println("Error in loading level");
                    }
                }
            });
        }
    }

    private void fadeInTransition(){
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
}