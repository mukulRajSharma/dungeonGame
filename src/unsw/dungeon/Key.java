package unsw.dungeon;
/**
 * Key class of Entity, also a collectible
 */
public class Key extends Entity implements Collection{

    public Key(int x, int y) {
        super(x, y);
    }

    @Override
    public String getItem() {
        return "Key";
    }

    @Override
    public boolean useItem(){
        return false;
    }
    
}