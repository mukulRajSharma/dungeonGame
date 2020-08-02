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

    public void move(){
        int playerX = d.getPlayer().getX();
        int playerY = d.getPlayer().getY();

        if(playerX > this.getX()){
            this.moveRight();
        } else if(playerX < this.getX()){
            this.moveLeft();
        }
        if(playerY > this.getY()){
            this.moveDown();
        } else if(playerY < this.getY()){
            this.moveUp();
        }
    }
}