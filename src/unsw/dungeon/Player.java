package unsw.dungeon;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends PlayerMovement {

    Inventory items;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        items = new Inventory();
    }

    private Collection getTouching(Object entity){
        System.out.println(entity.getClass().toString());
        for(Entity e: this.getDungeon().getEntities()){
            if(e == null) continue;
            if((this.getX() == e.getX()) && (this.getY() == e.getY()) && e.getClass().equals(entity.getClass())) {
                return (Collection)e;
            }
        }
        return null;
    }

    public void collectItem(){
        Collection item = getTouching(Collection.class);
        if(item != null){
            items.addItem(item);
            
        }
    }
}
