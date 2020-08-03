package unsw.dungeon;

public class EnemyHound extends Enemy{

    private Entity guardItem;
    private boolean direction = false;
    public EnemyHound(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y);
        guardItem = getItemWithId(id);
        
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
        

        if((this.getX() == guardItemX)&&(this.getY() == guardItemY)){
            if(direction == false){
                if(isTouching(this.getX()+1, this.getY(), new Wall(0, 0))){
                    moveUp();
                } else {
                    moveRight();
                    direction = true;
                }
            } else {
                if(isTouching(this.getX()-1, this.getY(), new Wall(0,0))){
                    moveDown();
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