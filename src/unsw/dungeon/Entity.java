package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;

    /**
     * Create an entity positioned in square (x,y)
     * @param x x coordinate of the entity
     * @param y y coordinate of the entity
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    /**
     * @return y coordinate of entity in int
     */
    public int getY() {
        return y().get();
    }
    /**
     * 
     * @return x coordinate of entity in int
     */
    public int getX() {
        return x().get();
    }

    /**
     * 
     * @param e the entity who's coordinates are to be compared with this object
     * @return true if 'e' is touching this object, false otherwise
     */
    public boolean isTouching(Entity e){
        return (this.x().get() == e.getX()) && (this.y().get() == e.getY());
    }
    /**
     * removes the object from the dungeon
     */
    public void remove(){
        this.x().set(-1); this.y().set(-1);
    }
    
}
