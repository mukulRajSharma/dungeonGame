package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends PlayerMovement {

    private Inventory items;
    private SimpleIntegerProperty health;
    private SimpleIntegerProperty invicibilityTurns;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        items = new Inventory();
        invicibilityTurns = new SimpleIntegerProperty();
        health = new SimpleIntegerProperty();
        health.set(1);
        invicibilityTurns.set(0);
    }
    /**
     * @return items in the player's inventory
     */
    public Inventory getInventory(){
        return items;
    }
    /**
     * 
     * @return the number of invinvibility turns left
     */
    public IntegerProperty getInvincibility(){
        return invicibilityTurns;
    }
    /**
     * 
     * @return health of the player (numerical)
     */
    public IntegerProperty getHealth(){
        return health;
    }
    /**
     * 
     * @param number sets health of the player
     */
    public void setHealth(int number){
        health.set(number);
    }
    /**
     * 
     * @return true if player touching enemy, false otherwise
     */
    private boolean touchingEnemy(){
        for(Enemy e: this.getDungeon().getEnemies()){
            if(this.isTouching(e)){
                return true;
            }
        }
        return false;
    }
    /**
     * consumes potion from inventory, increments invincibility counter
     */
    public void usePotion(){
        if(items.useItem(new Potion(0,0))){
            invicibilityTurns.set(5);
        }
    }
    /**
     * 
     * @return returns true is player has met a door in its path, false otherwise
     */
    private boolean checkDoor(){
        Door door = (Door)getTouching(new Door(0,0, 1));
        if(door != null){
            if(items.useItem(new Key(0,0, 0), door.getId())){
                door.openDoor();
            } else if(door.getOpen().get()){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * moves the player one step up, -1 in y direction
     */
    public void moveUp() {
        super.moveUp();
        if(!checkDoor()){
            super.moveDown();
        }
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveUp();
        }
        update();
        
    }
    /**
     * move the player one step down, +1 in y direction
     */
    public void moveDown() {
        super.moveDown();
        if(!checkDoor()){
            super.moveUp();
        }
        update();
    }
    /**
     * moves the player one step left, -1 in x direction
     */
    public void moveLeft() {
        super.moveLeft();
        if(!checkDoor()){
            super.moveRight();
        }
        update();
    }
    /**
     * moves the player one step right, +1 in x direction
     */
    public void moveRight() {
        super.moveRight();
        if(!checkDoor()){
            super.moveLeft();
        }
        update();
    }
    /**
     * removes the enemy touching the player(if pre-conditions met)
     */
    private void removeEnemy(){
        int toremove = 0;
        for(Enemy e: this.getDungeon().getEnemies()){
            if(this.isTouching(e)){
                toremove = this.getDungeon().getEnemies().indexOf(e);
                break;
            }
        }
        this.getDungeon().removeEntity(this.getDungeon().getEnemies().get(toremove));
    }
    /**
     * updated the players state after every move
     */
    private void update(){
        this.getDungeon().moveEnemies();
        if(touchingEnemy()){
            if(invicibilityTurns.intValue() > 0) {
                removeEnemy();
            } else if(items.useItem(new Weapon(0, 0, 5))){
                removeEnemy();
            } else {
                this.setHealth(0);
            }
        }
        Exit exit = (Exit)getTouching(new Exit(0, 0));
        if(exit != null){
            exit.setExit(true);
        } else {
            for(Exit e: this.getDungeon().getExits()){
                e.setExit(false);
            }
        }
        Item c = (Item)getTouching();
        if(c != null){
            items.addItem(c);
            Entity e = (Entity) c;
            this.getDungeon().removeEntity(e);
        }
        if(invicibilityTurns.get() > 0){
            invicibilityTurns.set(invicibilityTurns.intValue()-1);
        }
        Portal p = (Portal)getTouching(new Portal(0,0,0));
        if (p != null){
            for (Portal other: getDungeon().getPortals()){
                    if (other.getId()==p.getId() && (other.getX() != p.getX() || other.getY() != p.getY())){
                    x().set(other.getX());
                    y().set(other.getY());
                    break;
                }
            }
        }
        this.getDungeon().checkWin();
    }
}
