package unsw.dungeon;

public class Boulder extends PlayerMovement{

    public Boulder(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        // TODO Auto-generated constructor stub
    }
    // check for walls and boulders
    public boolean canMove(int x, int y)
    {
        if (isTouching(this.getX()+x, this.getY()+y, new Wall(0, 0)) || isTouching(this.getX()+x, this.getY()+y, new Boulder(getDungeon(),0, 0)))
            return false;
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