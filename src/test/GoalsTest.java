package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;

public class GoalsTest {
    @Test
    public void testAddGoals(){
        Dungeon d = new Dungeon(10, 10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        assertEquals(d.getGoals().toString(), "exit");

        Dungeon d1 = new Dungeon(0,0);
        JSONObject goals1 = new JSONObject().put("goal", "treasure");
        d1.setGoals(goals1);
        assertEquals(d1.getGoals().toString(), "treasure");

        Dungeon d2 = new Dungeon(0,1);
        JSONObject goals2 = new JSONObject().put("goal", "enemies");
        d2.setGoals(goals2);
        assertEquals(d2.getGoals().toString(), "enemies");

        Dungeon d6 = new Dungeon(0, 1);
        JSONObject goal6 = new JSONObject().put("goal", "boulders");
        d6.setGoals(goal6);
        assertEquals(d6.getGoals().toString(), "boulders");
        
        Dungeon d3 = new Dungeon(4,2);
        JSONArray a1 = new JSONArray().put(goals);
        a1.put(goals1);
        JSONObject goals3 = new JSONObject().put("goal", "AND");
        goals3.put("subgoals", a1);
        d3.setGoals(goals3);
        assertEquals(d3.getGoals().toString(), "AND, exit, treasure");

        Dungeon d4 = new Dungeon(69,420);
        JSONArray a2 = new JSONArray().put(goals2);
        a2.put(goals);
        JSONObject goals4 = new JSONObject().put("goal", "OR");
        goals4.put("subgoals", a2);
        d4.setGoals(goals4);
        assertEquals(d4.getGoals().toString(), "OR, enemies, exit");

        Dungeon d5 = new Dungeon(123,456);
        JSONArray a3 = new JSONArray().put(goals4);
        a3.put(goals1);
        JSONObject goals5 = new JSONObject().put("goal", "AND");
        goals5.put("subgoals", a3);
        d5.setGoals(goals5);
        assertEquals(d5.getGoals().toString(), "AND, OR, enemies, exit, treasure");
    }

    @Test
    public void testwinGame(){
        Dungeon d = new Dungeon(10, 10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        Exit e1 = new Exit(0,1);
        Player p = new Player(d, 0,0);

        d.setPlayer(p);
        d.addExit(e1);
        d.addEntity(p);
        d.addEntity(e1);
        assertEquals(d.getGoals().winGame(), false);
        p.moveDown();
        assertEquals(d.getGoals().winGame(), true);

        Dungeon d4 = new Dungeon(69,420);
        JSONObject goals2 = new JSONObject().put("goal", "enemies");
        JSONArray a2 = new JSONArray().put(goals2);
        a2.put(goals);
        JSONObject goals4 = new JSONObject().put("goal", "OR");
        goals4.put("subgoals", a2);
        d4.setGoals(goals4);
        assertEquals(d4.getGoals().toString(), "OR, enemies, exit");

        Exit e2 = new Exit(0,1);
        Player p1 = new Player(d4,0,0);
        Enemy en1 = new Enemy(d4,1,0);

        d4.setPlayer(p1);
        d4.addExit(e2);
        d4.addEnemy(en1);
        d4.addEntity(p1);
        d4.addEntity(e2);
        d4.addEntity(en1);

        assertEquals(d4.getGoals().winGame(), false);
        p1.moveDown();
        assertEquals(d4.getGoals().winGame(), true);
        p1.moveUp();
        assertEquals(d4.getGoals().winGame(), false);
        d4.removeEntity(en1);
        assertEquals(d4.getGoals().winGame(), true);
        p1.moveDown();
        assertEquals(d4.getGoals().winGame(), true);

        Dungeon d5 = new Dungeon(123,456);
        JSONObject goals1 = new JSONObject().put("goal", "treasure");
        JSONArray a3 = new JSONArray().put(goals4);
        a3.put(goals1);
        JSONObject goals5 = new JSONObject().put("goal", "AND");
        goals5.put("subgoals", a3);
        d5.setGoals(goals5);
        assertEquals(d5.getGoals().toString(), "AND, OR, enemies, exit, treasure");

        Exit e3 = new Exit(0,2);
        Player p2 = new Player(d5,0,0);
        Enemy en2 = new Enemy(d5,1,0);
        Treasure t1 = new Treasure(0,1);

        d5.addExit(e3);
        d5.setPlayer(p2);
        d5.addEnemy(en2);
        d5.addTreasure(t1);
        d5.addEntity(e3);
        d5.addEntity(p2);
        d5.addEntity(en2);
        d5.addEntity(t1);

        assertEquals(d5.getGoals().winGame(), false);
        d5.removeEntity(en2);
        assertEquals(d5.getGoals().winGame(), false);
        p2.moveDown();
        assertEquals(d5.getGoals().winGame(), true);    
        Enemy en3 = new Enemy(d5,10,10);
        d5.addEnemy(en3);
        d5.addEntity(en3);

        assertEquals(d5.getGoals().winGame(), false);
        p2.moveDown();
        assertEquals(d5.getGoals().winGame(), true);
    }
}