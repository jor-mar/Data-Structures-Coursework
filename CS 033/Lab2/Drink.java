/**
 * Represents a drink item that extends the PerishableItem class.
 * This class includes additional properties to indicate whether the drink is alcoholic or carbonated.
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * 2/27/25
 * CS 033 - Professor Ashraf
 * @see PerishableItem
 */
public class Drink extends PerishableItem {
    private boolean carbonated;
    private boolean alcoholic;

    /**
     * Constructs a new Drink object with the specified name, description, price, and calories.
     *
     * @param name        the name of the drink
     * @param description a brief description of the drink
     * @param price       the price of the drink
     * @param calories    the number of calories in the drink
     */
    public Drink(String name, String description, double price, int calories) {
        super(name, description, price, calories);
    }

    /**
     * Checks if the drink is carbonated.
     *
     * @return true if the drink is carbonated, false otherwise
     */
    public boolean isCarbonated() {
        return carbonated;
    }

    /**
     * Checks if the drink is alcoholic.
     *
     * @return true if the drink is alcoholic, false otherwise
     */
    public boolean isAlcoholic() {
        return alcoholic;
    }

    /**
     * Sets the carbonation status of the drink.
     *
     * @param carbonated true if the drink is carbonated, false otherwise
     */
    public void setCarbonated(boolean carbonated) {
        this.carbonated = carbonated;
    }

    /**
     * Sets the alcohol content status of the drink.
     *
     * @param alcoholic true if the drink is alcoholic, false otherwise
     */
    public void setAlcoholContent(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }
}