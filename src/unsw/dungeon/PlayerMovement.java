package unsw.dungeon;

/**
 * PlayerMovement class containing all the Movement realted functions for Movable objects
 */
public abstract class PlayerMovement extends Entity{

    private Dungeon dungeon;
    
    
    public PlayerMovement(Dungeon dungeon,int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    protected Dungeon getDungeon(){
        return this.dungeon;
    }

    /**
     * 
     * @param x x coordinate to be checked
     * @param y y coordinate to be checked
     * @param entity type of entity to be checked for
     * @return true if entity at given (x,y), false otherwise
     */
    public boolean isTouching(int x, int y, Entity entity){
        //System.out.println(entity.getClass().toString());
        for(Entity e: dungeon.getEntities()){
            if(e == null) continue;
            if((x == e.getX()) && (y == e.getY()) && e.getClass().equals(entity.getClass())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param entity entity whose surroundings are to be checked
     * @return entity encountered by the subject entity
     */
    public Entity getTouching(Entity entity){
        for(Entity e: dungeon.getEntities()){
            if(e == null) continue;
            if((this.getX() == e.getX()) && (this.getY() == e.getY()) && e.getClass().equals(entity.getClass())){
                return e;
            }
        }
        return null;
    }

    /**
     * 
     * @return entity in contact with this movable object
     */
    public Entity getTouching(){
        for(Entity e: dungeon.getEntities()){
            if(e == null) continue;
            if((this.getX() == e.getX()) && (this.getY() == e.getY()) && (e instanceof Collection)){
                return e;
            }
        }
        return null;
    }
    /**
     * 
     * @param x x coordinate to be checked
     * @param y y coordinate to be checked
     * @param b boulder who's coordinates are to be compared
     * @return true if movable entity touhces given boulder
     */
    public boolean isTouchingBoulder(int x, int y, Boulder b){
        if (x == b.getX() && y == b.getY())
            return true;
        return false;
    }

    public void moveUp() {
        if (getY() > 0 && (!isTouching(this.getX(), this.getY()-1, new Wall(0,0)))){
            for (Boulder b: dungeon.getBoulders()){
                if (isTouchingBoulder(this.getX(), this.getY()-1, b) && b.canMove(0, -1)){
                    b.moveUp();
                    y().set(getY() - 1);
                    return;
                }
                else if (isTouchingBoulder(this.getX(), this.getY()-1, b) && !b.canMove(0, -1))
                    return;
            }
            y().set(getY() - 1);
        }
            
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && (!isTouching(this.getX(), this.getY()+1, new Wall(0,0))))
        {
            for (Boulder b: dungeon.getBoulders()){
                if (isTouchingBoulder(this.getX(), this.getY()+1, b) && b.canMove(0, 1)){
                    b.moveDown();
                    y().set(getY() + 1);
                    return;
                }
                else if (isTouchingBoulder(this.getX(), this.getY()+1, b) && !b.canMove(0, 1))
                    return;
            }
            y().set(getY() + 1);
        }
            
    }

    public void moveLeft() {
        if (getX() > 0 && (!isTouching(this.getX() -1, this.getY(), new Wall(0,0))))
        {
            for (Boulder b: dungeon.getBoulders()){
                if (isTouchingBoulder(this.getX() -1, this.getY(), b) && b.canMove(-1, 0)){
                    b.moveLeft();
                    x().set(getX() - 1);
                    return;
                }
                else if (isTouchingBoulder(this.getX() -1, this.getY(), b) && !b.canMove(-1, 0))
                    return;
            }
            x().set(getX() - 1);
        }
            
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && (!isTouching(this.getX() +1, this.getY(), new Wall(0,0))))
        {
            for (Boulder b: dungeon.getBoulders()){
                if (isTouchingBoulder(this.getX() +1, this.getY(), b) && b.canMove(1, 0)){
                    b.moveRight();
                    x().set(getX() + 1);
                    return;
                }
                else if (isTouchingBoulder(this.getX() +1, this.getY(), b) && !b.canMove(1, 0))
                    return;
            }
            x().set(getX() + 1);
        }
    }
}