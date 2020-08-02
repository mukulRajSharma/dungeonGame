package unsw.dungeon;

public class WeaponStrategy implements ItemStrategy{
    private Weapon weapon;
    private Item item;
    public WeaponStrategy(Weapon w, Item item){
        weapon = w;
        this.item = item;
    }

    @Override
    public boolean useItem() {
        if(item.getClass().equals(Weapon.class)){
            return weapon.useItem();
        } else {
            return false;
        }
        
    }
    
}