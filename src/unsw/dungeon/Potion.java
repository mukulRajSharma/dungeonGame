package unsw.dungeon;

public class Potion extends Entity implements Collection{
    public Potion(int x, int y){
        super(x, y);
    }

    @Override
    public String getItem() {
        //TODO
        return "Potion";
    }
}