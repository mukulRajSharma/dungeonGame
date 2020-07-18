package unsw.dungeon;

public class Exit extends Entity{

    public Exit(int x, int y){
        super(x,y);
    }

    //TODO
	public String useItem() {
        return "YOU HAVE WON THE GAME";
        // TO WIN SCREEN
    }
    
    //TODO change this stub to a propper exit condition;
    public boolean exitConditionMet(){
        return false;
    }
}