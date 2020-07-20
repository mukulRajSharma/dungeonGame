package unsw.dungeon;

/**
 * Weapon class of Entity, also a collectible
 */
public class Weapon extends Entity implements Collection{

    private int weaponUse;

    public Weapon(int x, int y) {
        super(x, y);
        weaponUse = 5;
    }

    @Override
    public String getItem() {
        return "Weapon";
    }
    
    @Override
    /**
     * @return true if weapon can still be used, false otherwise
     */
    public boolean useItem(){
        if(weaponUse == 0){
            return false;
        } else {
            weaponUse -= 1;
        }
        return true;
    }
}