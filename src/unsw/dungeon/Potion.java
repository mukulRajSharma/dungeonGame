package unsw.dungeon;

public class Potion extends Entity implements Collection{
    public Potion(int x, int y){
        super(x, y);
    }

    @Override
    public Collection pickUp() {
        //TODO
        return this;
    }

    @Override
    public String getItem() {
        return "Potion";
    }
}