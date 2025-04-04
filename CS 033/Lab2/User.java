/**
 * The User interface represents a user with basic personal information.
 * It provides methods to retrieve the user's username, first name, last name, ID, and age.
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * 2/27/25
 * CS 033 - Professor Ashraf
 */
public interface User {
    /**
     * Gets user's username
     * @return the user's username
     */
    public String getUsername();

    /**
     * Gets user's first name
     * @return the user's first name
     */
    public String getFirstName();

    /**
     * Gets user's last name
     * @return the user's last name
     */
    public String getLastName();

    /**
     * Gets user's numeric ID
     * @return the user's ID
     */
    public int getID();

    /**
     * Gets the user's age
     * @return the user's age
     */
    public int getAge();
}