package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class for Door entity
 */
public class Door extends Entity{
    
    private SimpleBooleanProperty isOpen;
    private int id;

    public Door(int x, int y, int id) {
        super(x, y);
        isOpen = new SimpleBooleanProperty(false);
        this.id = id;
    }
    /**
     * 
     * @return state of door, true if open, false otherwise
     */
    public BooleanProperty getOpen(){
        return isOpen;
    }
    /**
     * set the state of the door to open/true
     */
    public void openDoor(){
        this.isOpen.set(true);
    }

    /**
     * 
     * @return the particular id of the door given
     */
    public int getId(){
        return this.id;
    }
    
}