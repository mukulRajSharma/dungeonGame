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
    private List<Treasure> treasures;
    private List<FloorSwitch> switchs;
    private List<Boulder> boulders;
    private Player player;
    private Goals goals;
    private List<Exit> exit;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.exit = new ArrayList<>();
        this.treasures = new ArrayList<>();
        this.switchs = new ArrayList<>();
        this.boulders = new ArrayList<>();
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

    public List<FloorSwitch> getFloorSwitchs(){
        return switchs;
    }

    public List<Exit> getExits(){
        return exit;
    }

    public List<Boulder> getBoulders(){
        return boulders;
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

    public void addSwitch(FloorSwitch s){
        switchs.add(s);
    }

    public void addExit(Exit e){
        exit.add(e);
    }

    public void addTreasure(Treasure t){
        treasures.add(t);
    }
    
    public void addBoulder(Boulder b){
        boulders.add(b);
    }

    public boolean boulderEndCondition(){
        for(FloorSwitch s: switchs) {
            if(!s.boulderOnTop()){
                return false;
            }
        }
        return true;
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
        if(treasures.size() == 0){
            return true;
        }
        return false;
    }

    public boolean enemyEndCondition(){
        if(enemies.size() == 0){
            return true;
        }
        return false;
    }

    public boolean checkWin(){
        return goals.winGame();
    }

    

    public void removeEntity(Entity e){
        if(!entities.contains(e)) return;
        e.remove();
        if(e.getClass().equals(new Enemy(this, 0, 0).getClass())){
            enemies.remove(e);
            entities.remove(e);
            return;
        }
        if(e.getClass().equals(new Treasure(0, 0).getClass())){
            treasures.remove(e);
            entities.remove(e);
            return;
        }
        entities.remove(e);
    }
}
