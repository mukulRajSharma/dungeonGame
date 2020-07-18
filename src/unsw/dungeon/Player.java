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
}
