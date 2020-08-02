package unsw.dungeon;

public class ItemStrategyFactory {

    public static ItemStrategy getStrat(Item item, Item item2){
        if(item.getClass().equals(Potion.class)){
            return new PotionStrategy((Potion)item, item2);
        } else if(item.getClass().equals(Weapon.class)){
            return new WeaponStrategy((Weapon)item, item2);
        } else {
            return null;
        }
    }

    public static ItemStrategy getStrat(Item item, Item item2, int id){
        if(item.getClass().equals(Key.class)){
            return new KeyStrategy((Key)item, id, item2);
        } else {
            return null;
        }
    }
}