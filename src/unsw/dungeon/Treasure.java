package unsw.dungeon;

import javafx.beans.property.SimpleBooleanProperty;

public class Treasure extends Entity implements Collection{

    public Treasure(int x, int y){
        super(x, y);
    }

    @Override
    public String getItem() {
        return "Treasure";
        // TODO Auto-generated method stub

    }

    @Override
    public boolean useItem() {
        return false;
    }
}