package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    Stage window;

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;

        window.setTitle("Dungeon");
        FXMLLoader loader = levelLoader("boulders.json");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window.setScene(scene);
        window.show();
    }

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
