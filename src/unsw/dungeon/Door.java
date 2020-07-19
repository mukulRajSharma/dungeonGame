package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity{

    private SimpleBooleanProperty isOpen;

    public Door(int x, int y) {
        super(x, y);
        isOpen = new SimpleBooleanProperty(false);
        // TODO Auto-generated constructor stub
    }

    public BooleanProperty getOpen(){
        return isOpen;
    }

    public void openDoor(){
        this.isOpen.set(true);
    }
    
}