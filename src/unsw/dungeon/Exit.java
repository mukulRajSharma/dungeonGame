package unsw.dungeon;

public class Exit extends Entity{

    public Exit(int x, int y){
        super(x,y);
    }

	public String useItem() {
        return "YOU HAVE WON THE GAME";
        // TO WIN SCREEN
	}
}