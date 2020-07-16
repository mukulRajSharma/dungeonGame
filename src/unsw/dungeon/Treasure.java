package unsw.dungeon;

public class Treasure extends Entity implements Collection{
    
    public Treasure(int x, int y){
        super(x, y);
    }

    @Override
    public Collection pickUp() {
        //TODO
        //also need to remove the icon and place it in the inventory
        return this;
    }

    @Override
    public String getItem() {
        return "Treasure";
        // TODO Auto-generated method stub

    }
}