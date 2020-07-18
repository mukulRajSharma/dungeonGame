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
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        items = new Inventory();
        health = new SimpleIntegerProperty();
        health.set(1);
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


    public void moveUp() {
        super.moveUp();
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveUp();
        }
        update();
        
    }

    public void moveDown() {
        super.moveDown();
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveDown();
        }
        update();
    }

    public void moveLeft() {
        super.moveLeft();
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveLeft();
        }
        update();
    }

    public void moveRight() {
        super.moveRight();
        for(Enemy e : this.getDungeon().getEnemies()){
            e.moveRight();
        }
        update();
    }

    private void update(){
        if(touchingEnemy()) setHealth(0);
        Exit exit = (Exit)getTouching(new Exit(0, 0));
        if(exit != null){
            exit.setExit(true);
        } else {
            for(Exit e: this.getDungeon().getExits()){
                e.setExit(false);
            }
        }
        this.getDungeon().checkWin();
    }
}
