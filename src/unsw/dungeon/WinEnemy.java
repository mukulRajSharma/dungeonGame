package unsw.dungeon;

public class WinEnemy implements winConditions{
    private Dungeon d;

    public WinEnemy(Dungeon d){
        this.d = d;
    }

    @Override
    public boolean checkWin() {
        return d.enemyEndCondition();
    }

    @Override
    public String toString(){
        return "enemies";
    }
    
}