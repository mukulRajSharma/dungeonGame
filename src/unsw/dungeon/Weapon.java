package unsw.dungeon;

public class Weapon extends Entity implements Collection{

    public Weapon(int x, int y) {
        super(x, y);
    }

    @Override
    public Collection pickUp() {
        // TODO Auto-generated method stub
        return this;
    }

    @Override
    public String getItem() {
        return "Weapon";
    }
    
}