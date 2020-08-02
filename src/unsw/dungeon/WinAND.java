package unsw.dungeon;

import java.util.ArrayList;

public class WinAND implements winConditions{
    private ArrayList<winConditions> goals;

    public WinAND(){
        goals = new ArrayList<>();
    }


    @Override
    public boolean checkWin() {
        for(winConditions w: goals){
            if(!w.checkWin()){
                return false;
            }
        }
        return true;
    }

    public void addGoals(winConditions w){
        goals.add(w);
    }

    @Override
    public String toString(){
        String temp = "AND";
        for(winConditions w: goals){
            temp = temp + ", " + w.toString();
        }
        return temp;
    }
    
}