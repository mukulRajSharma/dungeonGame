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
    private List<FloorSwitch> floorSwitchs;
    private List<Boulder> boulders;
    private List<Portal> portals;
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
        this.floorSwitchs = new ArrayList<>();
        this.boulders = new ArrayList<>();
        this.portals = new ArrayList<>();
        this.player = null;
        goals = new Goals(this);

    }

    /**
     * 
     * @param o the goals of the dungeon, the JSON object contains element goal: "goal of the dungeon"
     */
    public void setGoals(JSONObject o){
        //if(o == null) System.out.println("yeet");
        goals.addGoals(o);
    }

    /**
     * 
     * @return the goals Object associated with this dungeon
     */
    public Goals getGoals(){
        return goals;
    }

    /**
     * 
     * @return the width of the dungeon
     */
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @return the height of the dungeon
     */
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @return the player (user) associated with the dungeon
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 
     * @return all entitys of the dungeon including (enemy, exit, goal ,key, player, portal, weapon, portal ,door) 
     * in no particular order
     */
    public List<Entity> getEntities(){
        return entities;
    }

    /**
     * 
     * @return all enemy associated with the dungeon
     */
    public List<Enemy> getEnemies(){
        return enemies;
    }

    /**
     * 
     * @return all treasures associated with the dungeon
     */
    public List<Treasure> getTreasure(){
        return treasures;
    }

    /**
     * 
     * @return all switches associated with the dungeon
     */
    public List<FloorSwitch> getFloorSwitchs(){
        return floorSwitchs;
    }

    /**
     * 
     * @return all exits of the dungeon
     */
    public List<Exit> getExits(){
        return exit;
    }

    /**
     * 
     * @return all boulder entities in the dungeon
     */
    public List<Boulder> getBoulders(){
        return boulders;
    }

    /**
     * 
     * @return all portal entities in the dungeon
     */
    public List<Portal> getPortals(){
        return portals;
    }

    /**
     * 
     * @param player sets a particular player to the dungoen
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * 
     * @param enemy adds an enemy to the enemy list in the dungeon
     * does not add to entities
     */
    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    /**
     * 
     * @param entity adds an entity to the dungeon
     * does not add to entities
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    /**
     * 
     * @param s adds a switch to the dungeon
     * does not add to entities
     */
    public void addSwitch(FloorSwitch s){
        floorSwitchs.add(s);
    }

    /**
     * 
     * @param e adds a exit to the dungeon
     * does not add to entities
     * a dungeon can have more that 1 exit
     */
    public void addExit(Exit e){
        exit.add(e);
    }

    /**
     * 
     * @param t adds a treasure to the dungeon
     * does not add to entities
     */
    public void addTreasure(Treasure t){
        treasures.add(t);
    }
    
    /**
     * 
     * @param b adds a boulder to the dungeon
     * does not add to entities
     */
    public void addBoulder(Boulder b){
        boulders.add(b);
    }

    /**
     * 
     * @param p adds a portal to the dungeon
     * does not add to entities
     */
    public void addPortal(Portal p){
        portals.add(p);
    }
    
    /**
     * 
     * @return if the boulder end condition of the dungeon is met
     * If all switches has a boulder on top of it then return true, else false
     */
    public boolean boulderEndCondition(){
        for(FloorSwitch s: floorSwitchs) {
            if(!s.boulderOnTop()){
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @return if the exit end condition of the dungeon is met
     * If a player is touching a exit then this will return true, else false
     */
    public boolean exitEndCondition(){
        for(Exit e: exit){
            if(e.exitConditionMet()){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return if the treasure end condition of the dungeon is met
     * If the user has collected all treasures on the map this will return true, else false
     */
    public boolean treasureEndCondition(){
        if(treasures.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * 
     * @return if the enemy end condition of the dungeon is met
     * If the user has killed all enemys in the dungeon this will return true, else false
     */
    public boolean enemyEndCondition(){
        if(enemies.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * 
     * @return if all the dungeon conditions have been met return true, else false
     */
    public boolean checkWin(){
        return goals.winGame();
    }

    

    /**
     * 
     * @param e removes a particular entity from the dungeon.
     * It sets the entity's x value to -1 to remove from the display
     */
    public void removeEntity(Entity e){
        if(!entities.contains(e)) return;
        e.remove();
        if(e.getClass().equals(new Enemy(this, 0, 0).getClass())){
            enemies.remove(e);
        }
        if(e.getClass().equals(new Treasure(0, 0).getClass())){
            treasures.remove(e);
        }
        if(e.getClass().equals(new FloorSwitch(this, 0, 0).getClass())){
            floorSwitchs.remove(e);
        }
        if(e.getClass().equals(new Exit(0,0).getClass())){
            exit.remove(e);
        }
        entities.remove(e);
    }
}
