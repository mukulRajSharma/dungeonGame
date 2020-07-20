package unsw.dungeon;

import java.io.FileNotFoundException;

import org.json.JSONObject;

public class MockLoader extends DungeonControllerLoader {

    public MockLoader(JSONObject o) throws FileNotFoundException {
        super(o);
    }
    
}