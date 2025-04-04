/**
 * The Food class represents a type of perishable item that can be vegetarian or vegan.
 * It extends the PerishableItem class and includes additional properties to indicate whether
 * the food is vegetarian or vegan.
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * 2/27/25
 * CS 033 - Professor Ashraf
 * @see PerishableItem
 */
public class Food extends PerishableItem {
    private boolean vegetarian;
    private boolean vegan;

    /**
     * Constructs a new Food item with the specified name, description, price, and calories.
     *
     * @param name        the name of the food item
     * @param description the description of the food item
     * @param price       the price of the food item
     * @param calories    the number of calories in the food item
     */
    public Food(String name, String description, double price, int calories) {
        super(name, description, price, calories);
    }

    /**
     * Returns whether the food item is vegetarian.
     *
     * @return true if the food item is vegetarian, false otherwise
     */
    public boolean isVegetarian() {
        return vegetarian;
    }

    /**
     * Sets whether the food item is vegetarian.
     *
     * @param vegetarian true if the food item is vegetarian, false otherwise
     */
    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    /**
     * Returns whether the food item is vegan.
     *
     * @return true if the food item is vegan, false otherwise
     */
    public boolean isVegan() {
        return vegan;
    }

    /**
     * Sets whether the food item is vegan.
     *
     * @param vegan true if the food item is vegan, false otherwise
     */
    public void setVegan(boolean vegan) {
        this.vegan = vegan;
        if (vegan)
            vegetarian = true;
    }
}