package unsw.dungeon;

public class Exit extends Entity{

    private boolean exitcondition;

    public Exit(int x, int y){
        super(x,y);
        exitcondition = false;
    }

    //TODO
	public String useItem() {
        return "YOU HAVE WON THE GAME";
        // TO WIN SCREEN
    }
    
    //TODO change this stub to a propper exit condition;
    public boolean exitConditionMet(){
        return exitcondition;
    }

    public void setExit(boolean b){
        this.exitcondition = b;
    }
}