package unsw.dungeon;

public class Weapon extends Entity implements Collection{

    public Weapon(int x, int y) {
        super(x, y);
    }

    @Override
    public String getItem() {
        //TODO
        return "Weapon";
    }
    
}