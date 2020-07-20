package unsw.dungeon;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;


/**
 * Goals class to store the current status of the dungeon Goals
 */
public class Goals {
    private SimpleBooleanProperty goalsMet;
    private ArrayList<String> winConditions;
    private Dungeon dungeon;

    public Goals(Dungeon d){
        goalsMet = new SimpleBooleanProperty();
        goalsMet.set(false);
        winConditions = new ArrayList<String>();
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
     * @param goals converts JSONObject to Goal and adds all the goals to the dungeon
     */
    public void addGoals(JSONObject goals){
        String goal = goals.getString("goal");
        winConditions.add(goal);
        if(goal.equals("AND") || goal.equals("OR")){
            JSONArray subgoals = goals.getJSONArray("subgoals");
            for(int i = 0; i < subgoals.length(); i++){
                this.addGoals(subgoals.getJSONObject(i));
            }
        }
    }
    /**
     * 
     * @return true if player won the game.
     */
    public boolean winGame(){
        boolean win = hasWon(0);
        setGoalsMet(win);
        return win;
    }
    /**
     * 
     * @param curr the win condition index to be checked
     * @return true if the condition at position 'curr' has been met
     */
    private boolean hasWon(int curr){
        if(winConditions.get(curr).equals("AND")){
            if(winConditions.get(curr+1).equals("AND") || winConditions.get(curr+1).equals("OR")){
                if(this.hasWon(curr+1) && this.hasWon(curr+4)){
                    return true;
                } else {
                    return false;
                }
            } else {
                if(this.hasWon(curr+1) && this.hasWon(curr+2)){
                    return true;
                } else {
                    return false;
                }
            }
            
        } else if(winConditions.get(curr).equals("OR")){
            if(winConditions.get(curr+1).equals("AND") || winConditions.get(curr+1).equals("OR")){
                if(this.hasWon(curr+1) || this.hasWon(curr+4)){
                    return true;
                } else {
                    return false;
                }
            } else {
                if(this.hasWon(curr+1) || this.hasWon(curr+2)){
                    return true;
                } else {
                    return false;
                }
            }
        }
        boolean endCondition = false;
        switch(winConditions.get(curr)){
            case "treasure":
                endCondition = dungeon.treasureEndCondition();
                break;
            case "exit":
                endCondition = dungeon.exitEndCondition();
                break;
            case "boulders":
                endCondition = dungeon.boulderEndCondition();
                break;
            case "enemies":
                endCondition = dungeon.enemyEndCondition();
                break;
        }
        return endCondition;
    }


    @Override
    public String toString(){
        return winConditions.toString();
    }

    
}