package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;
import unsw.dungeon.Weapon;

public class PlayerMovementTest {
    @Test
    public void testIsTouching(){
        Dungeon d = new Dungeon(10,10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        Player p = new Player(d,0,0);
        Exit e1 = new Exit(0,0);
        
        d.setPlayer(p);
        d.addEntity(p);
        assertEquals(p.isTouching(p.getX(),p.getY(), e1), false);

        d.addEntity(e1);
        assertEquals(p.isTouching(p.getX(),p.getY(),e1), true);
        p.moveDown();
        assertEquals(p.isTouching(p.getX(),p.getY(),e1), false);
        p.moveUp();
        assertEquals(p.isTouching(p.getX(), p.getY(), e1), true);
        d.removeEntity(e1);
        assertEquals(p.isTouching(p.getX(), p.getY(), e1), false);

    };

    @Test
    public void testGetTouching(){
        Dungeon d = new Dungeon(10,10);
        Player p = new Player(d,0,0);
        Key k1 = new Key(0,0,1);

        d.addEntity(p);
        d.setPlayer(p);

        assertEquals(p.getTouching(new Key(0,0,1)), null);

        d.addEntity(k1);

        assertEquals(p.getTouching(new Key(0,0,1)), k1);

        d.removeEntity(k1);
        assertEquals(p.getTouching(new Key(0,0,1)), null);

        Exit e1 = new Exit(0,0);
        Weapon w1 = new Weapon(3, 4 ,5);
        d.addEntity(w1);
        d.addExit(e1);
        d.addEntity(e1);

        assertEquals(p.getTouching(new Weapon(0, 0 ,5)), null);
        assertEquals(p.getTouching(new Exit(0,0)), e1);
    }

    @Test
    public void testGetTouching_2(){
        Dungeon d = new Dungeon(10,10);
        Player p = new Player(d,0,0);
        Key k1 = new Key(0,0,1);

        d.addEntity(p);
        d.setPlayer(p);

        assertEquals(p.getTouching(), null);

        d.addEntity(k1);
        assertEquals(p.getTouching(), k1);

        Exit e1 = new Exit(0,0);
        d.removeEntity(k1);

        d.addEntity(e1);
        d.addExit(e1);
        assertEquals(p.getTouching(), null);

        Weapon w1 = new Weapon(0, 0 ,5);
        d.addEntity(w1);

        assertEquals(p.getTouching(), w1);
    }

    @Test
    public void testMovement(){
        Dungeon d = new Dungeon(10,10);
        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        Player p = new Player(d,0,0);

        d.addEntity(p);
        d.setPlayer(p);
        
        p.moveUp();
        p.moveUp();

        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);

        p.moveLeft();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);

        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);

        p.moveLeft();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);

        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);

        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 2);

        p.moveUp();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);

        Wall w1 = new Wall(0, 0);
        d.addEntity(w1);
        p.moveUp();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);

        Door door = new Door(1,1,1);
        d.addEntity(door);
        p.moveRight();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);

        Key key = new Key(0,2,6);
        d.addEntity(key);
        p.moveDown();
        p.moveUp();
        p.moveRight();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 1);

        Key key1 = new Key(0,2,1);
        d.addEntity(key1);
        p.moveDown();
        p.moveUp();
        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);
    }
}