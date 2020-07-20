package unsw.dungeon;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;



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

    public BooleanProperty complete(){
        return goalsMet;
    }

    public void setGoalsMet(boolean b){
        goalsMet.set(b);
    }


    // Stores goals like {AND, enemies, OR, exit, treasure} 
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

    public boolean winGame(){
        boolean win = hasWon(0);
        setGoalsMet(win);
        return win;
    }

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