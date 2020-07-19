package unsw.dungeon;

import java.util.ArrayList;

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
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        items = new Inventory();
        invicibilityTurns = new SimpleIntegerProperty();
        health = new SimpleIntegerProperty();
        health.set(1);
        invicibilityTurns.set(0);
    }

    public Inventory getInventory(){
        return items;
    }

    public IntegerProperty getInvincibility(){
        return invicibilityTurns;
    }

    public IntegerProperty getHealth(){
        return health;
    }

    public void setHealth(int number){
        health.set(number);
    }

    private boolean touchingEnemy(){
        for(Enemy e: this.getDungeon().getEnemies()){
            if(this.isTouching(e)){
                return true;
            }
        }
        return false;
    }

    public void usePotion(){
        if(items.useItem(new Potion(0,0))){
            invicibilityTurns.set(5);
        }
    }

    private boolean checkDoor(){
        Door door = (Door)getTouching(new Door(0,0));
        if(door != null){
            if(items.contains(new Key(0,0))){
                items.useItem(new Key(0,0));
                door.openDoor();
            } else {
                return false;
            }
        }
        return true;
    }


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

    public void moveDown() {
        super.moveDown();
        if(!checkDoor()){
            super.moveUp();
        }
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveDown();
        }
        update();
    }

    public void moveLeft() {
        super.moveLeft();
        if(!checkDoor()){
            super.moveRight();
        }
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveLeft();
        }
        update();
    }

    public void moveRight() {
        super.moveRight();
        if(!checkDoor()){
            super.moveLeft();
        }
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveRight();
        }
        update();
    }

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

    private void update(){
        if(touchingEnemy()){
            if(invicibilityTurns.intValue() > 0) {
                removeEnemy();
            } else if(items.useItem(new Weapon(0, 0))){
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
        Collection c = (Collection)getTouching();
        if(c != null){
            items.addItem(c);
            Entity e = (Entity) c;
            this.getDungeon().removeEntity(e);
        }
        
        if(invicibilityTurns.get() > 0){
            invicibilityTurns.set(invicibilityTurns.intValue()-1);
        }
        
        this.getDungeon().checkWin();
    }
}
