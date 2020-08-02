package unsw.dungeon;

public class PotionStrategy implements ItemStrategy{
    private Potion potion;
    private Item item;
    public PotionStrategy(Potion potion, Item item){
        this.potion = potion;
        this.item = item;
    }

    @Override
    public boolean useItem() {
        if(item.getClass().equals(Potion.class)){
            return potion.useItem();
        } else {
            return false;
        }
        
    }
    
}