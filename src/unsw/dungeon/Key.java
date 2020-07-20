package unsw.dungeon;
/**
 * Key class of Entity, also a collectible
 */
public class Key extends Entity implements Collection{

    private int id;

    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }

    @Override
    public String getItem() {
        return "Key";
    }

    @Override
    public boolean useItem(){
        return false;
    }

    public boolean checkId(int id){
        return this.id == id;
    }

    public int getId(){
        return this.id;
    }
    
}