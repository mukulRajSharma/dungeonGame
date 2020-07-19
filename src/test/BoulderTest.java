package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.FloorSwitch;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;
import unsw.dungeon.Weapon;
import unsw.dungeon.Boulder;

public class BoulderTest {
    @Test
    public void testBoulderEndCondition(){
        Dungeon dungeon = new Dungeon(10, 10);
        Player p = new Player(dungeon, 0, 0);
        Boulder b1 = new Boulder(dungeon,1,0);
        Boulder b2 = new Boulder(dungeon,1,1);

        dungeon.setPlayer(p);
        dungeon.addEntity(b1);
        dungeon.addEntity(b2);

        // check entities added
        assertEquals(dungeon.getBoulders().size(), 2);

        // push boulder right
        p.moveRight();
        assertEquals(b1.getX(), 0);
        assertEquals(b1.getY(), 2);

        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);
        
        // push boulder down
        p.moveDown();
        assertEquals(b2.getX(), 2);
        assertEquals(b2.getY(), 1);

        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        // push boulder left
        p.moveRight();
        p.moveRight();
        p.moveUp();
        p.moveLeft();
        assertEquals(b1.getX(), 0);
        assertEquals(b1.getY(), 1);

        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 2);
    }

    @Test
    public void testBoulderWall(){
        Dungeon dungeon = new Dungeon(10,10);

        Player p = new Player(dungeon,0,0);
        Boulder b1 = new Boulder(dungeon, 0, 1);
        Wall w1 = new Wall(0, 3);

        dungeon.setPlayer(p);
        dungeon.addEntity(b1);
        dungeon.addEntity(w1);

        // push boulder against wall
        assertEquals(b1.canMove(0, 1), true);
        p.moveRight();
        assertEquals(b1.getY(), 2);
        assertEquals(b1.getX(), 0);
        assertEquals(p.getY(), 1);
        assertEquals(p.getX(), 0);

        assertEquals(b1.canMove(0, 1), false);
        p.moveRight();
        assertEquals(b1.getY(), 2);
        assertEquals(b1.getX(), 0);
        assertEquals(p.getY(), 1);
        assertEquals(p.getX(), 0);
    }

    @Test
    public void testBoulderBoulder(){
        Dungeon d = new Dungeon(10,10);
        
        Player p = new Player(d, 1, 0);
        Boulder b1 = new Boulder(d, 1, 1);
        Boulder b2 = new Boulder(d, 1, 3);

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