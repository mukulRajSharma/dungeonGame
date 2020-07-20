package unsw.dungeon;

/**
 * Enemy class extending from PlayerMovement
 */
public class Enemy extends PlayerMovement{

    public Enemy(Dungeon dungeon, int x, int y){
        super(dungeon, x, y);
    }
    //implement Enemy movement AI here! At the moment the enemy just moves in that the player in moving
}