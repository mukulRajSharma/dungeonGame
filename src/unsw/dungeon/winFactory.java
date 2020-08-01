package unsw.dungeon;

public class winFactory {
    public static winConditions getWinCondition(String condition, Dungeon d){
        winConditions w = null;
        
        if(condition.equalsIgnoreCase("boulders")){
            w = new WinBoulder(d);
        } else if(condition.equalsIgnoreCase("exit")){
            w = new WinExit(d);
        } else if(condition.equalsIgnoreCase("OR")){
            w = new WinOr();
        } else if(condition.equalsIgnoreCase("AND")){
            w = new WinAND();
        } else if(condition.equalsIgnoreCase("treasure")){
            w = new WinTreasure(d);
        } else if(condition.equalsIgnoreCase("enemies")){
            w = new WinEnemy(d);
        }
        return w;
    }
}