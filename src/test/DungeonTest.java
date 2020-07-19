package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.Weapon;

public class DungeonTest {
    @Test
    public void testBoulderEndCondition(){
        Dungeon dungeon = new Dungeon(10, 10);

        Boulder b1 = new Boulder(dungeon, 0,0);
        Boulder b2 = new Boulder(dungeon,1,1);
        Boulder b3 = new Boulder(dungeon,2,2);

        dungeon.addEntity(b1);
        dungeon.addEntity(b2);
        dungeon.addEntity(b3);

        FloorSwitch f1 = new FloorSwitch(dungeon, 0, 0);
        FloorSwitch f2 = new FloorSwitch(dungeon, 1, 2);
        
        dungeon.addEntity(f1);
        dungeon.addSwitch(f1);
        assertEquals(dungeon.getFloorSwitchs().size(), 1);
        assertEquals(dungeon.boulderEndCondition(), true);

        dungeon.addEntity(f2);
        dungeon.addSwitch(f2);
        assertEquals(dungeon.getFloorSwitchs().size(), 2);
        assertEquals(dungeon.boulderEndCondition(), false);

        dungeon.removeEntity(f2);
        assertEquals(dungeon.getFloorSwitchs().size(), 1);
        assertEquals(dungeon.boulderEndCondition(), true);

        FloorSwitch f3 = new FloorSwitch(dungeon, 1, 2);
        dungeon.addEntity(f3);
        dungeon.addSwitch(f3);
        dungeon.removeEntity(f1);
        assertEquals(dungeon.getFloorSwitchs().size(), 1);
        assertEquals(dungeon.boulderEndCondition(), false);

        
        Boulder b4 = new Boulder(dungeon,1,2);
        dungeon.addEntity(b4);
        assertEquals(dungeon.getFloorSwitchs().size(), 1);
        assertEquals(dungeon.boulderEndCondition(), true);
    }

    @Test
    public void testtreasureEndCondition(){
        Dungeon dungeon = new Dungeon(10,10);
        JSONObject jsonGoal = new JSONObject().put("goal","exit");
        dungeon.setGoals(jsonGoal);
        Treasure t1 = new Treasure(0,1);
        Treasure t2 = new Treasure(1,1);
        Treasure t3 = new Treasure(3,2);

        Player p = new Player(dungeon,0,0);
        dungeon.addTreasure(t1);
        dungeon.addEntity(t1);
        dungeon.setPlayer(p);
        dungeon.addEntity(p);

        assertEquals(dungeon.treasureEndCondition(), false);
        p.moveDown();
        assertEquals(dungeon.treasureEndCondition(), true);

        dungeon.addEntity(t2);
        dungeon.addTreasure(t2);
        dungeon.addEntity(t3);
        dungeon.addTreasure(t3);

        assertEquals(dungeon.treasureEndCondition(), false);
        p.moveRight();
        assertEquals(dungeon.treasureEndCondition(), false);
        assertEquals(dungeon.getEntities().size(), 2);
        p.moveRight();
        p.moveRight();
        p.moveDown();
        assertEquals(dungeon.treasureEndCondition(), true);
    }

    @Test
    public void testEnemyEndCondition(){
        Dungeon d = new Dungeon(10,10);
        JSONObject jsonGoal = new JSONObject().put("goal","exit");
        d.setGoals(jsonGoal);

        Enemy e1 = new Enemy(d, 3, 0);
        Enemy e2 = new Enemy(d, 2, 4);
        Enemy e3 = new Enemy(d, 1, 3);

        Player p = new Player(d,0,0);

        assertEquals(d.enemyEndCondition(), true);
        d.setPlayer(p);
        d.addEnemy(e1);
        d.addEntity(e1);

        assertEquals(d.enemyEndCondition(), false);
        p.moveLeft();
        p.moveLeft();
        p.moveLeft();

        assertEquals(d.enemyEndCondition(), false);
        e1.moveRight();

        Weapon w1 = new Weapon(0, 1);
        d.addEntity(w1);
        p.moveDown();
        p.moveUp();
      
        assertEquals(d.enemyEndCondition(), false);
        p.moveLeft();
        assertEquals(d.enemyEndCondition(), true);

        d.addEnemy(e2);
        d.addEntity(e2);
        assertEquals(d.enemyEndCondition(), false);
        p.moveUp();
        p.moveUp();
        p.moveUp();
        p.moveUp();
        p.moveLeft();

        d.addEnemy(e3);
        d.addEntity(e3);

        p.moveLeft();
        assertEquals(d.enemyEndCondition(), false);
        p.moveUp();
        p.moveUp();
        p.moveUp();
        assertEquals(d.enemyEndCondition(), true);

        
    } 

    @Test
    public void testRemoveEntity(){
        Dungeon d = new Dungeon(10, 10);
        Enemy e1 = new Enemy(d, 0, 0);
        d.addEntity(e1);
        d.addEnemy(e1);
        d.removeEntity(e1);
        assertEquals(d.getEnemies().size(), 0);
        assertEquals(d.getEntities().size(), 0);

        Treasure t1 = new Treasure(0,0);
        d.addTreasure(t1);
        d.addEntity(t1);

        Key k1 = new Key(0, 0);
        d.addEntity(k1);

        d.removeEntity(k1);
        assertEquals(d.getEntities().size(), 1);
        d.removeEntity(t1);
        assertEquals(d.getEntities().size(), 0);
    }

}