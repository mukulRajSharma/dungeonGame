package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.DungeonController;
import unsw.dungeon.DungeonControllerLoader;
import unsw.dungeon.Exit;
import unsw.dungeon.Key;
import unsw.dungeon.MockLoader;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;
import unsw.dungeon.Wall;
import unsw.dungeon.Weapon;

public class JsonReaderTest {
    @Test
    public void testDungeonReader() throws IOException{
        
        
        MockLoader m = new MockLoader(createJSON());
        Dungeon d = m.load();

        assertEquals(d.getEntities().get(0).getClass(), new Wall(0, 0).getClass());
        assertEquals(d.getEntities().get(0).getX(),0);
        assertEquals(d.getEntities().get(0).getY(),0);

        assertEquals(d.getEntities().size(), 13);

        assertEquals(d.getEntities().get(11).getClass(), new Exit(0,0).getClass());
        assertEquals(d.getEntities().get(11).getX(), 5);
        assertEquals(d.getEntities().get(11).getY(), 5);

        assertEquals(d.getEntities().get(4).getClass(), new Weapon(0, 0, 5).getClass());
        assertEquals(d.getEntities().get(4).getX(), 0);
        assertEquals(d.getEntities().get(4).getY(), 3);

        assertEquals(d.getEntities().get(6).getClass(), new Potion(0, 0).getClass());
        assertEquals(d.getEntities().get(6).getX(), 1);
        assertEquals(d.getEntities().get(6).getY(), 0);

        assertEquals(d.getPlayer().getX(), 0);
        assertEquals(d.getPlayer().getY(), 2);

        assertEquals(d.getEnemies().size(), 1);
        assertEquals(d.getBoulders().size(), 1);
        assertEquals(d.getPortals().size(), 2);

        assertEquals(d.getHeight(), 10);
        assertEquals(d.getWidth(), 10);
    }

    private JSONObject createJSON(){
        JSONObject testDungeon = new JSONObject();
        testDungeon.put("width", 10);
        testDungeon.put("height", 10);

        JSONObject wall = new JSONObject();
        wall.put("x", 0);
        wall.put("y", 0);
        wall.put("type", "wall");
        JSONArray entities = new JSONArray().put(wall);

        JSONObject portal1 = new JSONObject();
        portal1.put("id", 1);
        portal1.put("x", 0);
        portal1.put("y", 1);
        portal1.put("type", "portal");
        entities.put(portal1);

        JSONObject portal2 = new JSONObject();
        portal2.put("id", 1);
        portal2.put("x", 9);
        portal2.put("y", 9);
        portal2.put("type", "portal");
        entities.put(portal2);

        JSONObject player = new JSONObject();
        player.put("x", 0);
        player.put("y", 2);
        player.put("type", "player");
        entities.put(player);

        JSONObject weapon = new JSONObject();
        weapon.put("x", 0);
        weapon.put("y", 3);
        weapon.put("type", "sword");
        entities.put(weapon);

        JSONObject key = new JSONObject();
        key.put("x", 0);
        key.put("y", 4);
        key.put("type", "key");
        entities.put(key);

        JSONObject potion = new JSONObject();
        potion.put("x", 1);
        potion.put("y", 0);
        potion.put("type", "invincibility");
        entities.put(potion);

        JSONObject treasure = new JSONObject();
        treasure.put("x", 2);
        treasure.put("y", 0);
        treasure.put("type", "treasure");
        entities.put(treasure);

        JSONObject enemy = new JSONObject();
        enemy.put("x", 3);
        enemy.put("y", 0);
        enemy.put("type", "enemy");
        entities.put(enemy);

        JSONObject floorSwitch = new JSONObject();
        floorSwitch.put("x", 10);
        floorSwitch.put("y", 10);
        floorSwitch.put("type", "switch");
        entities.put(floorSwitch);

        JSONObject door = new JSONObject();
        door.put("x", 6);
        door.put("y", 6);
        door.put("type", "door");
        entities.put(door);

        JSONObject exit = new JSONObject();
        exit.put("x", 5);
        exit.put("y", 5);
        exit.put("type", "exit");
        entities.put(exit);

        JSONObject boulder = new JSONObject();
        boulder.put("x", 4);
        boulder.put("y", 2);
        boulder.put("type", "boulder");
        entities.put(boulder);

        testDungeon.put("entities",entities);

        JSONObject goals = new JSONObject();
        goals.put("goal", "AND");
        JSONArray subGoals = new JSONArray();
        JSONObject goal1 = new JSONObject();
        goal1.put("goal", "enemies");
        subGoals.put(goal1);
        JSONObject goal2 = new JSONObject();
        goal2.put("goal", "treasure");
        subGoals.put(goal2);
        goals.put("subgoals", subGoals);
        testDungeon.put("goal-condition", goals);
        
        return testDungeon;
    }
}