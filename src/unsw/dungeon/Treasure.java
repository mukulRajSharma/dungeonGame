package unsw.dungeon;

/**
 * Treasure class of Entity, also a collectible
 */
public class Treasure extends Entity implements Item{

    public Treasure(int x, int y){
        super(x, y);
    }

    @Override
    public boolean useItem() {
        return false;
    }

    @Override
    public boolean getUsed() {
        return false;
    }


}