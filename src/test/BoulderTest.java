package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Exit;

public class BoulderTest {
    @Test
    public void testBoulderMovement(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player p = new Player(dungeon, 0, 0);
        Boulder b1 = new Boulder(dungeon,1,0);
        Boulder b2 = new Boulder(dungeon,1,1);

        JSONObject goals = new JSONObject().put("goal", "exit");
        dungeon.setGoals(goals);
        //Player p = new Player(d,0,0);
        Exit e1 = new Exit(0,0);

        dungeon.setPlayer(p);
        dungeon.addEntity(p);
        dungeon.addEntity(b1);
        dungeon.addEntity(b2);
        dungeon.addBoulder(b1);
        dungeon.addBoulder(b2);
        
        // check entities added
        //assertEquals(dungeon.getBoulders().size(), 2);
        // push boulder right
        p.moveRight();
        assertEquals(b1.getX(), 2);
        assertEquals(b1.getY(), 0);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
        
        // push boulder down
        p.moveDown();
        assertEquals(b2.getX(), 1);
        assertEquals(b2.getY(), 2);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        // push boulder left
        p.moveRight();
        p.moveRight();
        p.moveUp();
        p.moveLeft();
        assertEquals(b1.getX(), 1);
        assertEquals(b1.getY(), 0);

        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 0);
    }

    @Test
    public void testBoulderWall(){
        Dungeon dungeon = new Dungeon(10,10);

        Player p = new Player(dungeon,0,0);
        Boulder b1 = new Boulder(dungeon, 1, 0);
        Wall w1 = new Wall(3, 0);

        JSONObject goals = new JSONObject().put("goal", "exit");
        dungeon.setGoals(goals);
        //Player p = new Player(d,0,0);
        Exit e1 = new Exit(9,0);

        dungeon.setPlayer(p);
        dungeon.addEntity(p);
        dungeon.addEntity(b1);
        dungeon.addBoulder(b1);
        dungeon.addEntity(w1);

        // push boulder against wall
        assertEquals(b1.canMove(1, 0), true);
        p.moveRight();
        assertEquals(b1.getY(), 0);
        assertEquals(b1.getX(), 2);
        assertEquals(p.getY(), 0);
        assertEquals(p.getX(), 1);

        assertEquals(b1.canMove(1, 0), false);
        p.moveRight();
        assertEquals(b1.getY(), 0);
        assertEquals(b1.getX(), 2);
        assertEquals(p.getY(), 0);
        assertEquals(p.getX(), 1);
        assertEquals(w1.getY(), 0);
        assertEquals(w1.getX(), 3);


    }

    @Test
    public void testBoulderBoulder(){
        Dungeon d = new Dungeon(10,10);
        
        Player p = new Player(d, 1, 0);
        Boulder b1 = new Boulder(d, 1, 1);
        Boulder b2 = new Boulder(d, 1, 3);

        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        //Player p = new Player(d,0,0);
        Exit e1 = new Exit(9,0);

        d.setPlayer(p);
        d.addEntity(p);
        d.addEntity(b1);
        d.addEntity(b2);
        d.addBoulder(b1);
        d.addBoulder(b2);


        p.moveDown();
        assertEquals(b1.getY(), 2);
        assertEquals(b1.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(p.getX(), 1);

        assertEquals(b1.canMove(0, 1), false);
        p.moveDown();
        assertEquals(b1.getY(), 2);
        assertEquals(b1.getX(), 1);
        assertEquals(p.getY(), 1);
        assertEquals(p.getX(), 1);
        assertEquals(b2.getY(), 3);
        assertEquals(b2.getX(), 1);
        
    } 


}