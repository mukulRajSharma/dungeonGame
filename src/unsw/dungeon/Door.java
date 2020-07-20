package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Class for Door entity
 */
public class Door extends Entity{
    
    private SimpleBooleanProperty isOpen;

    public Door(int x, int y) {
        super(x, y);
        isOpen = new SimpleBooleanProperty(false);
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
    
}