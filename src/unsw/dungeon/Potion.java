package unsw.dungeon;

/**
 * Potion class of Entity, also a collectible
 */
public class Potion extends Entity implements Collection{
    public Potion(int x, int y){
        super(x, y);
    }

    @Override
    public String getItem() {
        return "Potion";
    }

    @Override
    public boolean useItem() {
        return false;
    }
}