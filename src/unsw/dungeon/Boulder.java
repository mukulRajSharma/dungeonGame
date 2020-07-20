package unsw.dungeon;
/**    
 * Class for Boulder Entity
 */
public class Boulder extends PlayerMovement{

    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }
    /**
     * 
     * @param x # of steps to move on x axis
     * @param y # of steps to move on y axis
     * @return
     */
    public boolean canMove(int x, int y)
    {
        if (this.getX()+x < 0 || this.getY()-y < 0)
            return false;
        if(this.getX()+x <= getDungeon().getWidth() && this.getY()+y <= getDungeon().getHeight())
        {
            if (isTouching(this.getX()+x, this.getY()+y, new Wall(0, 0)) || isTouching(this.getX()+x, this.getY()+y, new Boulder(getDungeon(),0, 0)))
                return false;
        }
        else{
            return false;
        }
        return true;
    }
    public void moveUp(){
        this.y().set(getY()-1);
    }
    public void moveDown(){
        this.y().set(getY()+1);
    }
    public void moveleft(){
        this.x().set(getX()-1);
    }
    public void moveRight(){
        this.x().set(getX()+1);
    }
    
}