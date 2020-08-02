package unsw.dungeon;

public class WinTreasure implements winConditions{
    private Dungeon d;

    public WinTreasure(Dungeon d){
        this.d = d;
    }

    @Override
    public boolean checkWin() {
        return d.treasureEndCondition();
    }

    @Override
    public String toString(){
        return "treasure";
    }
    
}