package unsw.dungeon;

public class WinBoulder implements winConditions{
    private Dungeon d;

    public WinBoulder(Dungeon d){
        this.d = d;
    }

    @Override
    public boolean checkWin() {
        return d.boulderEndCondition();
    }

    @Override
    public String toString(){
        return "boulders";
    }
}