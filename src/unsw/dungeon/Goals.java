package unsw.dungeon;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * Goals class to store the current status of the dungeon Goals
 */
public class Goals {
    private SimpleBooleanProperty goalsMet;
    private winConditions win;
    private Dungeon dungeon;

    public Goals(Dungeon d){
        goalsMet = new SimpleBooleanProperty();
        goalsMet.set(false);
        win = null;
        this.dungeon = d;
    }
    /**
     * 
     * @return true if all goal conditions are met, false otherwise
     */
    public BooleanProperty complete(){
        return goalsMet;
    }
    /**
     * 
     * @param b sets goalsMet property to 'b'
     */
    public void setGoalsMet(boolean b){
        goalsMet.set(b);
    }

    /**
     * 
     * @param goals converts a json object to specific win Conditions
     */
    public void createGoals(JSONObject goals){
        win = addGoals(goals);
        //if(win == null) System.out.println("yeet");
    }

    private winConditions addGoals(JSONObject goals){
        String goal = goals.getString("goal");
        winConditions temp = winFactory.getWinCondition(goal, dungeon);
        if(goal.equals("AND")){
            JSONArray subgoals = goals.getJSONArray("subgoals");
            WinAND tempA = (WinAND)temp;
            for(int i = 0; i < subgoals.length(); i++){
                tempA.addGoals(this.addGoals(subgoals.getJSONObject(i)));
            }
        }
        if(goal.equals("OR")){
            JSONArray subgoals = goals.getJSONArray("subgoals");
            WinOr tempB = (WinOr)temp;
            for(int i = 0; i < subgoals.length(); i++){
                tempB.addGoals(this.addGoals(subgoals.getJSONObject(i)));
            }
        }
        return temp;
    }
    /**
     * 
     * @return true if player won the game.
     */
    public boolean winGame(){
        if(win.checkWin()){
            this.setGoalsMet(true);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return win.toString();
    }

    
}