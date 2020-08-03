package unsw.dungeon;

/**
 * Enemy class extending from PlayerMovement
 */
public class Enemy extends PlayerMovement{

    public Enemy(Dungeon dungeon, int x, int y){
        super(dungeon, x, y);
    }

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