package unsw.dungeon;

import org.junit.platform.console.shadow.picocli.CommandLine.Help;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelpController {
    @FXML
    private ImageView playerImg;
    @FXML
    private Button closeBtn;

    public HelpController(){
        
    }
    @FXML
    public void handleCloseBtn ()throws Exception{
        Stage tmp = (Stage)closeBtn.getScene().getWindow();
        tmp.close();
        
    }
}