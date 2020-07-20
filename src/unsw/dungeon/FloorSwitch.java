package unsw.dungeon;

/**
 * FloorSwitch class of Entity
 */
public class FloorSwitch extends Entity{
	private Dungeon dungeon;

	public FloorSwitch(Dungeon d, int x, int y) {
		super(x, y);
		this.dungeon = d;
	}

	/**
	 * 
	 * @return true if boulder on top of switch, false otherwise
	 */
	public boolean boulderOnTop(){
		for(Entity e: dungeon.getEntities()){
			if(this.isTouching(e) && e.getClass().equals(new Boulder(dungeon,0, 0).getClass())){
				return true;
			}
		}
		return false;
	}
    
}