package unsw.dungeon;
/**
 * Key class of Entity, also a collectible
 */
public class Key extends Entity implements Item{

    private int id;
    private boolean used;

    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
        used = false;
    }

    @Override
    public boolean useItem(){
        if(used){
            return false;
        } else {
            this.used = true;
            return true;
        }
        
    }
    /**
     * 
     * @param id 
     * @return true if the id given is the same as this key's,false if not
     */
    public boolean checkId(int id){
        return this.id == id;
    }

    /**
     * 
     * @return the id of the key
     */
    public int getId(){
        return this.id;
    }
    
}