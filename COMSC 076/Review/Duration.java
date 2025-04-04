/**
 * A class representing a time duration in terms of hours and minutes.
 * 
 * @author Jordan Marcelo
 * @version 1.0
 */
class Duration {
    private int hours;
    private int minutes;

    /**
     * Default constructor that initializes the duration to 0 hours and 0 minutes.
     */
    public Duration() {
        hours = 0;
        minutes = 0;
    }

    /**
     * Constructor that initializes the duration with a specified number of hours and minutes.
     * 
     * @param hours the number of hours
     * @param minutes the number of minutes
     * @throws IllegalArgumentException if hours or minutes are negative, or if minutes are greater than 59.
     */
    public Duration(int hours, int minutes) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative.");
        }
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative.");
        }
        if (minutes >= 60) {
            throw new IllegalArgumentException("Minutes cannot be 60 or greater.");
        }
        
        this.hours = hours;
        this.minutes = minutes;
    }

    /**
     * Gets the number of hours of this duration.
     * 
     * @return the number of hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * Gets the number of minutes of this duration.
     * 
     * @return the number of minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the number of hours of this duration.
     * 
     * @param hours the number of hours to set
     * @throws IllegalArgumentException if hours are negative
     */
    public void setHours(int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative.");
        }

        this.hours = hours;
    }

    /**
     * Sets the number of minutes of this duration.
     * 
     * @param minutes the number of minutes to set
     * @throws IllegalArgumentException if minutes are negative or greater than 59.
     */
    public void setMinutes(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes cannot be negative.");
        }
        if (minutes >= 60) {
            throw new IllegalArgumentException("Minutes cannot be 60 or above.");
        }

        this.minutes = minutes;
    }

    /**
     * Returns a string representation of this duration in the format "hours:minutes".
     * 
     * @return a string representation of the duration
     */
    @Override
    public String toString() {
        String stringHours = String.valueOf(hours);
        String stringMinutes = String.valueOf(minutes);

        if (hours < 10) {
            stringHours = "0" + stringHours;
        }

        if (minutes < 10) {
            stringMinutes = "0" + stringMinutes;
        }

        return stringHours + ":" + stringMinutes;
    }

    /**
     * Converts a string in the format "hours:minutes" to a Duration object.
     * 
     * @param durationString the string representation of a duration
     * @return a Duration object corresponding to the input string
     * @throws IllegalArgumentException if the string does not have a colon
     * @throws NumberFormatException if durationString cannot be parsed besides colon
     */
    public static Duration fromString(String durationString) {
        int colonIndex = durationString.indexOf(":");

        if (colonIndex == -1) {
            throw new IllegalArgumentException("String must be in \"hours:minutes\" format.");
        }

        String stringHours = durationString.substring(0, colonIndex);
        String stringMinutes = durationString.substring(colonIndex + 1);

        int hours = Integer.parseInt(stringHours);
        int minutes = Integer.parseInt(stringMinutes);

        return new Duration(hours, minutes);
    }

    /**
     * Adds another duration to this duration. The result is stored in the current object.
     * 
     * @param other the duration to add to this duration
     * @return this duration object representing the updated duration after addition
     */
    public Duration add(Duration other) {
        int totalMinutes = minutes + other.minutes;
        hours += other.hours + totalMinutes / 60;
        minutes = totalMinutes % 60;
        return this;
    }
}