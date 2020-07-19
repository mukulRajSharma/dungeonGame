package unsw.dungeon;

public class Portal extends Entity{
    private int id;
    public Portal(int id, int x, int y){
        super(x, y);
        this.id = id;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}