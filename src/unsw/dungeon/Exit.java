package unsw.dungeon;

public class Exit extends Entity{

    private boolean exitcondition;

    public Exit(int x, int y){
        super(x,y);
        exitcondition = false;
    }

    public boolean exitConditionMet(){
        return exitcondition;
    }

    public void setExit(boolean b){
        this.exitcondition = b;
    }
}