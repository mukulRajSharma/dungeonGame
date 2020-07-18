package unsw.dungeon;

public class Key extends Entity implements Collection{

    public Key(int x, int y) {
        super(x, y);
    }

    @Override
    public String getItem() {
        // TODO Auto-generated method stub
        return "Key";
    }
    
}