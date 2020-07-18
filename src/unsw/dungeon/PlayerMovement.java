package unsw.dungeon;

public abstract class PlayerMovement extends Entity{

    private Dungeon dungeon;
    
    
    public PlayerMovement(Dungeon dungeon,int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    protected Dungeon getDungeon(){
        return this.dungeon;
    }

    
    public boolean isTouching(int x, int y, Object entity){
        //System.out.println(entity.getClass().toString());
        for(Entity e: dungeon.getEntities()){
            if(e == null) continue;
            if((x == e.getX()) && (y == e.getY()) && e.getClass().equals(entity.getClass())) {
                return true;
            }
        }
        return false;
    }

        
    public Entity getTouching(Entity entity){
        for(Entity e: dungeon.getEntities()){
            if(e == null) continue;
            if((this.getX() == e.getX()) && (this.getY() == e.getY()) && e.getClass().equals(entity.getClass())){
                return e;
            }
        }
        return null;
    }

    public Entity getTouching(){
        for(Entity e: dungeon.getEntities()){
            if(e == null) continue;
            if((this.getX() == e.getX()) && (this.getY() == e.getY()) && (e instanceof Collection)){
                return e;
            }
        }
        return null;
    }

    public void moveUp() {
        if (getY() > 0 && (!isTouching(this.getX(), this.getY()-1, new Wall(0,0))))
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && (!isTouching(this.getX(), this.getY()+1, new Wall(0,0))))
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (getX() > 0 && (!isTouching(this.getX() -1, this.getY(), new Wall(0,0))))
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && (!isTouching(this.getX() +1, this.getY(), new Wall(0,0))))
            x().set(getX() + 1);
    }
}