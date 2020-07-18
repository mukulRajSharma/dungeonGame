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
    private List<Exit> exit;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.exit = new ArrayList<>();
        this.player = null;
        goals = new Goals(this);
    }

    public void setGoals(JSONObject o){
        //if(o == null) System.out.println("yeet");
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

    public List<Exit> getExits(){
        return exit;
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
        for(Exit e: exit){
            if(e.exitConditionMet()){
                return true;
            }
        }
        return false;
    }

    public boolean treasureEndCondition(){
        return false;
    }

    public boolean enemyEndCondition(){
        return false;
    }

    public boolean checkWin(){
        return goals.winGame();
    }

    public void addExit(Exit e){
        exit.add(e);
    }
}
