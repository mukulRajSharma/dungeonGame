package unsw.dungeon;

import java.util.ArrayList;

public class Inventory{

    ArrayList<Item> items;

    public Inventory(){
        items = new ArrayList<Item>();
    }

    public boolean useItem(Item item){
        for(Item i: items){
            if(useItemstrat(ItemStrategyFactory.getStrat(i, item))){
                return true;
            }
        }
        return false;
    }

    public boolean useItem(Item item , int id){
        for(Item i: items){
            if(useItemstrat(ItemStrategyFactory.getStrat(i, i, id))){
                return true;
            }
        }
        return false;
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

    /*
    public boolean useItem(Object o){
        boolean flag = false;
        boolean contains = false;
        int indexRemove = 0;
        if(o.getClass().equals(Key.class)){
            for(Item c: items){
                if(c.getClass().equals(o.getClass())){
                    Key k = (Key)o;
                    Key c1 = (Key)c;
                    if(k.checkId(c1.getId())){
                        contains = true;
                        indexRemove = items.indexOf(c);
                        flag = true;
                    }
                }
            }
        } else {
            for(Item c: items){
                if(c.getClass().equals(o.getClass())){
                    contains = true;
                    if(!c.useItem()){
                        indexRemove = items.indexOf(c);
                        flag = true;
                    }
                    break;
                }
            }
        }
        if(flag){
            items.remove(indexRemove);
        }
        return contains;
        
    }
    */  
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
    
}