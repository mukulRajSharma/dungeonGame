package unsw.dungeon;

import java.util.ArrayList;

public class Inventory implements Collection{

    ArrayList<Collection> items;

    public Inventory(){
        items = new ArrayList<Collection>();
    }

    //Displays all items in the inventory as a string
    @Override
    public String getItem() {
        String output = "";
        for(Collection c : items){
            output = output + "\n" + c.getItem();
        }
        return output;
    }

    @Override
    public boolean useItem(){
        return false;
    }

    public boolean contains(Entity e){
        for(Collection c: items){
            if(e.getClass().equals(c.getClass())){
                return true;
            }
        }
        return false;
    }

    public boolean useItem(Object o){
        boolean flag = false;
        boolean contains = false;
        int indexRemove = 0;
        for(Collection c: items){
            if(c.getClass().equals(o.getClass())){
                contains = true;
                if(!c.useItem()){
                    indexRemove = items.indexOf(c);
                    flag = true;
                }
                break;
            }
        }
        if(flag){
            items.remove(indexRemove);
        }
        return contains;
    }

    public void addItem(Collection c){
        if(c == null) return;
        items.add(c);
    }

    public void removeItem(Collection c){
        if(c == null) return;
        if(items.contains(c)){
            items.remove(c);
        }
    }
    
}