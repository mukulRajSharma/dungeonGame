/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private List<Enemy> enemies;
    private Player player;
    private Goals goals;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.player = null;
    }

    public void setGoals(JSONObject o){
        goals.addGoals(o);
    }

    public Goals getGoals(){
        return goals;
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public List<Enemy> getEnemies(){
        return enemies;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public boolean boulderEndCondition(){
        return false;
    }

    public boolean exitEndCondition(){
        return false;
    }

    public boolean treasureEndCondition(){
        return false;
    }

    public boolean enemyEndCondition(){
        return false;
    }

    public boolean hasWon(){
        return goals.winGame();
    }
}
