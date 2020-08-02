package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    Stage window;

    /**
     * 
     * @param primaryStage the stage that will be the display window for the output
     * Starts the JavaFX output scene
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        StartMenuController controller = new StartMenuController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("start_menu.fxml"));
        loader.setController(controller);

        // window.setTitle("Dungeon");
        // FXMLLoader loader = levelLoader("portals.json");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        window.setTitle("Dungeon Escape");
        window.setScene(scene);
        window.show();
        
    }

    /**
     * Depending on the input this will load the particular Fxml file and create the dungeon ready for display
     * @param dungeonName
     * @return
     * @throws IOException
     */
    private FXMLLoader levelLoader(String dungeonName) throws IOException {
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(dungeonName);
        DungeonController controller = dungeonLoader.loadController();
        FXMLLoader load = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
        load.setController(controller);
        return load;
    }

    public static void main(String[] args) {
        launch(args);
    }
}