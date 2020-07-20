package unsw.dungeon;

/**
 * Treasure class of Entity, also a collectible
 */
public class Treasure extends Entity implements Collection{

    public Treasure(int x, int y){
        super(x, y);
    }

    @Override
    public String getItem() {
        return "Treasure";

    }

    @Override
    public boolean useItem() {
        return false;
    }
}