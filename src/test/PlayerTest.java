package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Weapon;

public class PlayerTest {

    @Test
    public void checkhealth(){
        Dungeon d = new Dungeon(10, 10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);

        Player p = new Player(d, 0 ,0);
        d.setPlayer(p);
        d.addEntity(p);

        p.moveRight();

        Enemy e1 = new Enemy(d,0,0);
        p.moveLeft();
        assertEquals(p.getHealth().get(), 1);
        p.moveRight();
        d.addEnemy(e1);
        d.addEntity(e1);
        p.moveLeft();
        
        assertEquals(p.getHealth().get(), 0);

        Dungeon d1 = new Dungeon(10, 10);
        JSONObject goals1 = new JSONObject().put("goal", "exit");
        d1.setGoals(goals1);
        Player p1 = new Player(d1, 0 ,0);
        d1.setPlayer(p1);
        d1.addEntity(p1);

        Weapon w1 = new Weapon(1,0);
        d1.addEntity(w1);

        p1.moveRight();

        Enemy e2 = new Enemy(d1, 0, 0);
        d1.addEnemy(e2);
        d1.addEntity(e2);
        p1.moveLeft();

        assertEquals(p1.getHealth().get(), 1);
    }

    @Test
    public void testExits(){
        Dungeon d = new Dungeon(10, 10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);

        Player p = new Player(d, 0 ,0);
        d.setPlayer(p);
        d.addEntity(p);

        Exit exit = new Exit(0,1);
        d.addEntity(exit);
        d.addExit(exit);
        p.moveRight();
        assertEquals(exit.exitConditionMet(), false);
        p.moveLeft();
        p.moveDown();
        assertEquals(exit.exitConditionMet(), true);
        p.moveLeft();
        assertEquals(exit.exitConditionMet(), true);
        p.moveRight();
        assertEquals(exit.exitConditionMet(), false);
    }

    @Test
    public void testCollectingItems(){
        Dungeon d = new Dungeon(10, 10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);

        Player p = new Player(d, 0 ,0);
        d.setPlayer(p);
        d.addEntity(p);

        Key key = new Key(1,0);
        d.addEntity(key);
        p.moveRight();
        assertEquals(key.getX(), -1);
        assertEquals(p.getInventory().contains(key), true);

        Potion po = new Potion(0,0);
        d.addEntity(po);
        p.moveLeft();
        assertEquals(po.getX(), -1);
        assertEquals(p.getInventory().contains(po), true);
        assertEquals(p.getInventory().contains(key), true);


        Weapon w1 = new Weapon(0,1);
        d.addEntity(w1);
        p.moveDown();
        assertEquals(w1.getX(), -1);
        assertEquals(p.getInventory().contains(po), true);
        assertEquals(p.getInventory().contains(key), true);
        assertEquals(p.getInventory().contains(w1), true);
    }

    @Test
    public void testPotion(){
        Dungeon d = new Dungeon(10, 10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);

        Player p = new Player(d, 0 ,0);
        d.setPlayer(p);
        d.addEntity(p);

        Potion po = new Potion(0,1);
        d.addEntity(po);
        
        p.moveDown();
        assertEquals(p.getInventory().contains(po), true);

        p.usePotion();
        Enemy e1 = new Enemy(d,0,0);
        d.addEnemy(e1);
        d.addEntity(e1);

        p.moveUp();
        assertEquals(e1.getX(), -1);
        assertEquals(d.getEnemies().size(), 0);
        assertEquals(p.getHealth().get(), 1);

        p.moveDown();

        Enemy e2 = new Enemy(d,0,0);
        d.addEnemy(e2);
        d.addEntity(e2);
        p.moveUp();
        assertEquals(e2.getX(), -1);
        assertEquals(d.getEnemies().size(), 0);
        assertEquals(p.getHealth().get(), 1);
    
        p.moveDown();
        Enemy e3 = new Enemy(d,0,0);
        d.addEnemy(e3);
        d.addEntity(e3);
        p.moveUp();
        assertEquals(d.getEnemies().size(), 0);
        assertEquals(p.getHealth().get(), 1);
        

        Enemy e4 = new Enemy(d,0,0);
        d.addEnemy(e4);
        d.addEntity(e4);
        p.moveDown();
        assertEquals(d.getEnemies().size(), 1);
        assertEquals(p.getHealth().get(), 0);    
    }

    @Test
    public void testDoors(){
        Dungeon d = new Dungeon(12,34);
        JSONObject goal = new JSONObject().put("goal", "exit");
        d.setGoals(goal);

        Door door = new Door(0, 1);
        Key key = new Key(1, 0);
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        d.addEntity(door);
        d.addEntity(key);
        d.addEntity(p);

        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);

        p.moveRight();
        assertEquals(p.getInventory().contains(key), true);
        p.moveLeft();
        p.moveDown();
        assertEquals(p.getX(),0);
        assertEquals(p.getY(), 1);
        assertEquals(p.getInventory().contains(key), false);

        
    }
}