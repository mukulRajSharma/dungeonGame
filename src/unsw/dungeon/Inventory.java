package unsw.dungeon;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory{

    private ObservableList<Item> items;
    private int inventoryMaxSize;

    public Inventory(){
        items = FXCollections.observableArrayList();
        inventoryMaxSize = 5;
    }

    public ObservableList<Item> getItems(){
        return items;
    }

    public boolean useItem(Item item){
        for(Item i: items){
            ItemStrategy i2 = ItemStrategyFactory.getStrat(i, item);
            if(i2 != null){
                if(useItemstrat(i2)){
                    updateInventory();
                    return true;
                }
            }
        }
        updateInventory();
        return false;
    }

    public boolean useItem(Item item , int id){
        for(Item i: items){
            ItemStrategy i2 = ItemStrategyFactory.getStrat(i, item, id);
            if(i2 != null){
                if(useItemstrat(i2)){
                    updateInventory();
                    return true;
                }
            }
        }
        updateInventory();
        return false;
    }

    public void updateInventory(){
        ArrayList<Item> list = new ArrayList<>();
        for(Item i : items) {
            if(!i.getUsed()){
                list.add(i);
            }
        }
        items.clear();
        for(Item i: list){
            items.add(i);
        }
    }

    private boolean useItemstrat(ItemStrategy strat){
        return strat.useItem();
    }

    /**
     * For testing purposes
     * @return all the items in the inventory
     */
    public boolean contains(Entity e){
        for(Item c: items){
            if(e.getClass().equals(c.getClass())){
                if(e.getClass().equals(Key.class)){
                    Key k = (Key) e;
                    Key c1 = (Key)c;
                    if(k.checkId(c1.getId())){
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public void addItem(Item c){
        if(c == null) return;
        items.add(c);
    }

    public void removeItem(Item c){
        if(c == null) return;
        if(items.contains(c)){
            items.remove(c);
        }
    }

    public int getMaxSize(){
        return inventoryMaxSize;
    }

    public int currSize(){
        return items.size();
    }
    
}