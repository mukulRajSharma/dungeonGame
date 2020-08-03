package unsw.dungeon;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.platform.console.shadow.picocli.CommandLine.Help;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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

    @FXML
    private RadioButton darkBtn;
    @FXML
    private RadioButton lightBtn;
    @FXML
    private ToggleGroup themeGroup;
    /**
     * initializes the end controller to display one of the end screens
     */
    public StartMenuController(){
        themeGroup = new ToggleGroup();
        // darkBtn.setToggleGroup(themeGroup);
        // lightBtn.setToggleGroup(themeGroup);
        // themeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
        //     public void changed(ObservableValue<? extends Toggle> ov,
        //         Toggle old_toggle, Toggle new_toggle) {
        //             if (themeGroup.getSelectedToggle() == darkBtn) {    
        //                 handleDarkBtn();
        //             }                
        //             else{
        //                 handleLightBtn();
        //             }
        //         }
        // });
    }
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
        FXMLLoader loader = levelLoader("easy_boulders.json");
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
        FXMLLoader loader = levelLoader("portals.json");
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
        FXMLLoader loader = levelLoader("extreme.json");
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
        // Stage boss = (Stage)root.getScene().getWindow();
        // boss.close();
        Stage window = new Stage();
        //FXMLLoader loader = levelLoader("advanced.json");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help.fxml"));
        HelpController help = new HelpController();
        loader.setController(help);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        window.setScene(scene);
        window.show();
    }
    @FXML
    public void handleDarkBtn(){
        lightBtn.setSelected(false);
        startLabel.setStyle("-fx-background-color: black;-fx-text-fill: white;");
        root.setStyle("-fx-background-color:grey;");       
        try{
            FileWriter fw = new FileWriter("src/unsw/dungeon/Style.css", false);
            String headStyle = "#headLabel { background-color: black;-fx-background-color: black;}";
            String textStyle = "#screenText {-fx-text-fill:white;background-color: black;-fx-background-color: black;}";
            String bg = "#bg {background-color: grey;-fx-background-color:grey;}";
            fw.write(headStyle+textStyle+bg);
            fw.close();
        }
        catch(IOException e){
            System.out.println("error opening css file");
        }
        
    }
    @FXML
    public void handleLightBtn(){
        darkBtn.setSelected(false);
        startLabel.setStyle("-fx-background-color: white;-fx-text-fill: black;");
        root.setStyle("-fx-background-color: #fcba03;");
        try{
            FileWriter fw = new FileWriter("src/unsw/dungeon/Style.css", false);
            PrintWriter pw = new PrintWriter(fw);
            String headStyle = "#headLabel { background-color: white;-fx-background-color: white; }";
            String textStyle = "#screenText {-fx-text-fill:black;background-color: white;-fx-background-color: white;}";
            String bg = "#bg {background-color: #fcba03;-fx-background-color:#fcba03;}";
            pw.print(headStyle+textStyle+bg);
            pw.close();
            fw.close();
        }
        catch(IOException e){
            System.out.println("error opening css file");
        }
        
    }
    
}
