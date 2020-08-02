package unsw.dungeon;

public class KeyStrategy implements ItemStrategy{
    private Key key;
    private Item item;
    private int externalId;

    public KeyStrategy(Key key,int id, Item item){
        this.externalId = id;
        this.key = key;
        this.item = item;
    }

    @Override
    public boolean useItem() {
        if(item.getClass().equals(Key.class)){
            if(key.checkId(externalId)){
                return key.useItem();
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
}