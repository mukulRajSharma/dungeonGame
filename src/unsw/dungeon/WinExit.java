package unsw.dungeon;

public class WinExit implements winConditions{
    private Dungeon d;

    public WinExit(Dungeon d){
        this.d = d;
    }

    @Override
    public boolean checkWin() {
        return d.exitEndCondition();
    }

    @Override
    public String toString(){
        return "exit";
    }
    
}