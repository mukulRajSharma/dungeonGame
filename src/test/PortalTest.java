package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.sound.sampled.Port;

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
import unsw.dungeon.*;


public class PortalTest {
    
    @Test
    public void testPlayerPortal(){
        Dungeon d = new Dungeon(10, 10);
        Player p = new Player(d, 0, 0);
        Portal p1 = new Portal(1, 1, 0);
        Portal p2 = new Portal(1, 5, 5);

        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        Exit e1 = new Exit(0,0);

        d.addEntity(p1);
        d.addEntity(p2);
        d.addPortal(p1);
        d.addPortal(p2);
        d.setPlayer(p);

        p.moveRight();
        assertEquals(p.getX(), 5);
        assertEquals(p.getY(), 5);
    }

    @Test
    public void testMultiplePortals(){
        Dungeon d = new Dungeon(10, 10);
        Player p = new Player(d, 0, 0);
        Portal p1 = new Portal(1, 1, 0);
        Portal p2 = new Portal(1, 5, 5);
        Portal p3 = new Portal(2, 5, 6);
        Portal p4 = new Portal(2, 8, 8);

        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        Exit e1 = new Exit(0,0);

        d.addEntity(p1);
        d.addEntity(p2);
        d.addPortal(p1);
        d.addPortal(p2);
        d.addEntity(p3);
        d.addEntity(p4);
        d.addPortal(p3);
        d.addPortal(p4);

        d.setPlayer(p);
        
        p.moveRight();
        assertEquals(p.getX(), 5);
        assertEquals(p.getY(), 5);

        p.moveDown();
        assertEquals(p.getX(), 8);
        assertEquals(p.getY(), 8);
    }

    @Test
    public void testEntityPortal(){
        Dungeon d = new Dungeon(10, 10);
        Player p = new Player(d, 0, 0);
        Portal p1 = new Portal(1, 2, 0);
        Portal p2 = new Portal(1, 5, 5);
        Boulder b1 = new Boulder(d, 1, 0);

        JSONObject goals = new JSONObject().put("goal", "exit");
        d.setGoals(goals);
        Exit e1 = new Exit(0,0);

        d.addBoulder(b1);
        d.addEntity(b1);
        d.addEntity(p1);
        d.addEntity(p2);
        d.addPortal(p1);
        d.addPortal(p2);
        d.setPlayer(p);

        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);
        assertEquals(b1.getX(), 2);
        assertEquals(b1.getY(), 0);
    }



}