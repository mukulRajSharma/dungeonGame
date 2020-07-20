package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image weaponImage;
    private Image keyImage;
    private Image potionImage;
    private Image treasureImage;
    private Image enemyImage;
    private Image portalImage;
    private Image boulderImage;
    private Image doorCloseImage;
    private Image floorSwitchImage;
    private Image exitImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image((new File("images/human_new.png")).toURI().toString());
        wallImage = new Image((new File("images/brick_brown_0.png")).toURI().toString());
        weaponImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        keyImage = new Image((new File("images/key.png")).toURI().toString());
        potionImage = new Image((new File("images/bubbly.png")).toURI().toString());
        treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        enemyImage = new Image((new File("images/gnome.png")).toURI().toString());
        portalImage = new Image((new File("images/portal.png")).toURI().toString());
        boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        doorCloseImage = new Image((new File("images/closed_door.png")).toURI().toString());
        floorSwitchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        exitImage = new Image((new File("images/exit.png")).toURI().toString());
    }

    
    public DungeonControllerLoader(JSONObject obj){
        super(obj);
        entities = new ArrayList<>();
        playerImage = null;
        wallImage = null;
        weaponImage = null;
        keyImage = null;
        potionImage = null;
        treasureImage = null;
        enemyImage = null;
        portalImage = null;
        boulderImage = null;
        doorCloseImage = null;
        floorSwitchImage = null;
        exitImage = null;
    }

    /**
     * associates a player with the player image
     */
    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    /**
     * associates a wall with the wall image
     */
    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }

    /**
     * associates a weapon with the weapon image
     */
    @Override
    public void onLoad(Weapon weapon){
        ImageView view = new ImageView(weaponImage);
        addEntity(weapon, view);
    }

    /**
     * associates a key with the key image
     */
    @Override
    public void onLoad(Key key){
        ImageView view = new ImageView(keyImage);
        addEntity(key, view);
    }

    /**
     * associates a potion with the potion image
     */
    @Override
    public void onLoad(Potion potion){
        ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
    }

    /**
     * associates a treasure with the treasure image
     */
    @Override
    public void onLoad(Treasure treasure){
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }

    /**
     * associates a enemy with the enemy image
     */
    @Override
    public void onLoad(Enemy enemy){
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }

    /**
     * associates a portal with the portal image
     */
    @Override
    public void onLoad(Portal portal){
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }

    /**
     * associates a boulder with the boulder image
     */
    @Override
    public void onLoad(Boulder boulder){
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }

    /**
     * associates a door with the door closed image
     */
    @Override
    public void onLoad(Door door){
        ImageView view = new ImageView(doorCloseImage);
        addEntity(door, view);
    }

    /**
     * associates a switch with the switch image
     */
    @Override
    public void onLoad(FloorSwitch floorSwitch){
        ImageView view = new ImageView(floorSwitchImage);
        addEntity(floorSwitch, view);
    }

    /**
     * associates an exit with the exit image
     */
    @Override
    public void onLoad(Exit exit){
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }

    private void addEntity(Entity entity, ImageView view) {
        entities.add(view);
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
