package unsw.dungeon;

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