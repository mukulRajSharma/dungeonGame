package unsw.dungeon;

import java.util.ArrayList;

public class WinOr implements winConditions {
    private ArrayList<winConditions> goals;

    public WinOr(){
        goals = new ArrayList<>();
    }


    @Override
    public boolean checkWin() {
        for(winConditions w: goals){
            if(w.checkWin()){
                return true;
            }
        }
        return false;
    }

    public void addGoals(winConditions w){
        goals.add(w);
    }

    @Override
    public String toString(){
        String temp = "OR";
        for(winConditions w: goals){
            temp = temp + ", " + w.toString();
        }
        return temp;
    }
}