package unsw.dungeon;

/**
 * Class for Exit entity
 */
public class Exit extends Entity{

    private boolean exitcondition;

    public Exit(int x, int y){
        super(x,y);
        exitcondition = false;
    }
    /**
     * 
     * @return true if all exit conditions are met, false otherwise.
     */
    public boolean exitConditionMet(){
        return exitcondition;
    }
    /**
     * 
     * @param b sets the exitcondition for this exit to 'b'
     */
    public void setExit(boolean b){
        this.exitcondition = b;
    }
}