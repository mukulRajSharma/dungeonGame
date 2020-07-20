package unsw.dungeon;

/**
 * Portal class of Entity
 */
public class Portal extends Entity{
    private int id;
    public Portal(int id, int x, int y){
        super(x, y);
        this.id = id;
    }
    /**
     * 
     * @return id of the portal
     */
    public int getId(){
        return id;
    }
    /**
     * 
     * @param id set the id of the portal
     */
    public void setId(int id){
        this.id = id;
    }
}