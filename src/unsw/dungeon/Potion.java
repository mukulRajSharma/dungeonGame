package unsw.dungeon;

/**
 * Potion class of Entity, also a collectible
 */
public class Potion extends Entity implements Item{
    private boolean used;

    public Potion(int x, int y){
        super(x, y);
        used = false;
    }

    @Override
    public boolean useItem() {
        if(used){
            return false;
        } else {
            used = true;
            return true;
        }
    }

    @Override
    public boolean getUsed(){
        return used;
    }
}