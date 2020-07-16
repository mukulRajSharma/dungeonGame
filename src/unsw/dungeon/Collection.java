package unsw.dungeon;

public interface Collection{
    /**
     * Returns null if item cannot be picked up.
     */
    public Collection pickUp();
    public String getItem();
}