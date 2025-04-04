/**
 * Represents an item that can be bought.
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * 2/27/25
 * CS 033 - Professor Ashraf
 */
public interface BuyableItem {

    /**
     * Gets the price of the item.
     *
     * @return the price of the item
     */
    public double getPrice();

    /**
     * Gets the name of the item.
     *
     * @return the name of the item
     */
    public String getName();

    /**
     * Gets the description of the item.
     *
     * @return the description of the item
     */
    public String getDescription();

    /**
     * Gets the stock quantity of the item.
     *
     * @return the stock quantity of the item
     */
    public int getStock();

    /**
     * Checks if the item is available for purchase.
     *
     * @return true if the item is available, false otherwise
     */
    public boolean isAvailable();

    /**
     * Increases the inventory by 1.
     */
    public void reStock();

    /**
     * Increases the inventory by the specified amount.
     *
     * @param added the number of items to add to the inventory
     */
    public void reStock(int added);
    
    /**
     * Decreases the inventory by 1.
     *
     * @throws IllegalStateException if removing stock would result in a negative inventory
     */
    public void removeStock();

    /**
     * Decreases the inventory by the specified amount.
     *
     * @param removed the number of items to remove from the inventory
     * @throws IllegalStateException if removing stock would result in a negative inventory
     */
    public void removeStock(int removed);
}