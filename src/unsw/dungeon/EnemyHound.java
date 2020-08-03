package unsw.dungeon;

public class EnemyHound extends Enemy{

    private Entity guardItem;
    private boolean direction = false;
    private int itemid;
    private int delayTime;

    public EnemyHound(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y);
        guardItem = getItemWithId(id);
        itemid = id;
        delayTime = 0;
    }

    private Entity getItemWithId(int id){
        Dungeon d = this.getDungeon();
        for(Entity e: d.getEntities()){
            if(e instanceof Item){
                Item i = (Item)e;
                if(i.getClass().equals(Key.class)){
                    Key k = (Key)i;
                    if(k.getId() == id){
                        return k;
                    }
                }
            }
        }
        return null;
    }

    public void move(){
        int guardItemX = guardItem.getX();
        int guardItemY = guardItem.getY();
        
        if(delayTime > 0){
            delayTime--;
        } else {
            if((this.getX() == guardItemX)&&(this.getY() == guardItemY)){
                if(direction == false){
                    if(isTouching(this.getX()+1, this.getY(), new Wall(0, 0))){
                        if(isTouching(this.getX(), this.getY()+1, new Wall(0,0))){
                            moveDown();
                        } else {
                            moveUp();
                        }
                    } else {
                        moveRight();
                        direction = true;
                    }
                } else {
                    if(isTouching(this.getX()-1, this.getY(), new Wall(0,0))){
                        if(isTouching(this.getX()-1, this.getY(), new Wall(0,0))){
                            moveUp();
                        } else {
                            moveDown();
                        }
                    } else {
                        moveLeft();
                        direction = false;
                    }
                }
            } else {
                move(guardItem);
            }
        }   
    }

    public void changeGuard(Entity e){
        delayTime = 5;
        this.guardItem = e;
    }

    public Entity getGuardItem(){
        return guardItem;
    }

    public int getItemId(){
        return itemid;
    }

    public void setItemId(int id){
        itemid = id;
    }
        
}