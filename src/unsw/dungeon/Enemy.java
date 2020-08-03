package unsw.dungeon;

/**
 * Enemy class extending from PlayerMovement
 */
public class Enemy extends PlayerMovement{

    private Dungeon d;

    public Enemy(Dungeon dungeon, int x, int y){
        super(dungeon, x, y);
        this.d = dungeon;
    }
    //implement Enemy movement AI here! At the moment the enemy just moves in that the player in moving

    public void move(Entity e){
        int eX = e.getX();
        int eY = e.getY();

        if(eX > this.getX()){
            this.moveRight();
        } else if(eX < this.getX()){
            this.moveLeft();
        }
        if(eY > this.getY()){
            this.moveDown();
        } else if(eY < this.getY()){
            this.moveUp();
        }
    }
}