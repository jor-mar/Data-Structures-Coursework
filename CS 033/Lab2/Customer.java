import java.util.ArrayList;

/**
 * The Customer class represents a customer with a unique ID, personal details, and a shopping cart.
 * It implements the User interface and provides methods to interact with the customer's data and shopping cart.
 * 
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * @date 2/27/25
 * CS 033 - Professor Ashraf
 */
public class Customer implements User {
    private String firstName;
    private String lastName;
    private String username;
    private int age;
    private static int nextID = 1;
    private int id;
    private final ArrayList<BuyableItem> shoppingCart = new ArrayList<>();

    /**
     * Constructs a new Customer with the given username, first name, last name, and age.
     * Assigns a unique ID to the customer.
     * 
     * @param username The customer's username.
     * @param firstName The customer's first name.
     * @param lastName The customer's last name.
     * @param age The customer's age. Must be non-negative.
     * @throws IllegalArgumentException if the age is negative.
     */
    public Customer(String username, String firstName, String lastName, int age) {
        if (age < 0)
            throw new IllegalArgumentException("Age cannot be negative");
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.id = nextID;
        nextID++;
    }

    /**
     * Gets the username of the customer.
     * 
     * @return The customer's username.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Gets the first name of the customer.
     * 
     * @return The customer's first name.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the customer.
     * 
     * @return The customer's last name.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the unique ID of the customer.
     * 
     * @return The customer's unique ID.
     */
    @Override
    public int getID() {
        return id;
    }

    /**
     * Gets the age of the customer.
     * 
     * @return The customer's age.
     */
    @Override
    public int getAge() {
        return age;
    }

    /**
     * Checks if this customer is equal to another object.
     * Two customers are equal if they have the same ID.
     * 
     * @param other The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof Customer && id == ((Customer) other).id;
    }

    /**
     * Generates a hash code based on the customer's ID.
     * 
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    /**
     * Adds an item to the customer's shopping cart if it is available and meets any age restrictions.
     * 
     * @param item The item to be purchased.
     * @return True if the item was successfully added, false otherwise.
     */
    public boolean buy(Object item) {
        if (!(item instanceof BuyableItem))
            return false;
        if (item instanceof Drink drink && drink.isAlcoholic() && age < 21)
            return false;
        BuyableItem buyingItem = (BuyableItem) item;
        if (!buyingItem.isAvailable())
            return false;
        return shoppingCart.add(buyingItem);
    }

    /**
     * Calculates the total cost of all items in the shopping cart.
     * 
     * @return The total cost of the shopping cart.
     */
    public double getCost() {
        double sum = 0;
        for (BuyableItem item : shoppingCart)
            sum += item.getPrice();
        return sum;
    }

    /**
     * Finalizes the purchase by removing stock for each item in the cart and clearing the cart.
     * Prints a summary of the purchase.
     */
    public void buy() {
        for (BuyableItem item : shoppingCart)
            item.removeStock();
        System.out.println(firstName + " " + lastName + " bought $" + getCost() + " in items from their shopping cart.");
        shoppingCart.clear();
    }

    /**
     * Calculates the total calorie count of all perishable items in the shopping cart.
     * 
     * @return The total calorie count of perishable items.
     */
    public int getCalories() {
        int sum = 0;
        for (BuyableItem item : shoppingCart)
            if (item instanceof PerishableItem perishable)
                sum += perishable.getCalories();
        return sum;
    }
}