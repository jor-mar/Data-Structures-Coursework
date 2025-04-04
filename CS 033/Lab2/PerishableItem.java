/**
 * Represents a perishable item that can be bought, with an expiration date and calorie count.
 * Implements the {@code BuyableItem} interface.
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * 2/27/25
 * CS 033 - Professor Ashraf
 */
abstract class PerishableItem implements BuyableItem {
    private Date expirationDate;
    private int calories;
    private String name;
    private double price;
    private String description;
    private static int inventory;

    /**
     * Constructs a new {@code PerishableItem} with the specified name, description, price, and calories.
     *
     * @param name        the name of the perishable item
     * @param description a brief description of the perishable item
     * @param price       the price of the perishable item
     * @param calories    the number of calories in the perishable item
     */
    public PerishableItem(String name, String description, double price, int calories) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
    }

    /**
     * Sets the expiration date using numeric month, day, and year.
     *
     * @param month the month (1-12)
     * @param day   the day of the month
     * @param year  the year
     */
    public void setExpirationDate(int month, int day, int year) {
        expirationDate = new Date(month, day, year);
    }

    /**
     * Sets the expiration date using the month's name, day, and year.
     *
     * @param month the name of the month (e.g., "January")
     * @param day   the day of the month
     * @param year  the year
     * @throws IllegalArgumentException if the specified month is invalid
     */
    public void setExpirationDate(String month, int day, int year) {
        expirationDate = new Date(month, day, year);
    }

    /**
     * Retrieves the expiration date as a string.
     *
     * @return the expiration date in "Month/Day/Year" format
     * @throws IllegalStateException if the expiration date has not been set
     */
    public String getExpirationDate() {
        if (expirationDate == null)
            throw new IllegalStateException("Expiration date has not been set");
        return expirationDate.toString();
    }

    /**
     * Sets the number of calories in the perishable item.
     *
     * @param calories the number of calories
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     * Retrieves the number of calories in the perishable item.
     *
     * @return the calorie count
     */
    public int getCalories() {
        return calories;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isAvailable() {
        return inventory > 0;
    }

    @Override
    public int getStock() {
        return inventory;
    }

    @Override
    public void reStock() {
        reStock(1);
    }

    @Override
    public void reStock(int added) {
        inventory += added;
    }

    @Override
    public void removeStock() {
        removeStock(1);
    }

    @Override
    public void removeStock(int removed) {
        if (inventory - removed < 0) {
            throw new IllegalStateException("Cannot have negative stock");
        }
        inventory -= removed;
    }

    /**
     * Represents a date with month, day, and year components.
     * @author Jordan Marcelo, Evan Chou
     * @version 1.0
     * 2/27/25
     * CS 033 - Professor Ashraf
     */
    private class Date {
        private int month;
        private int day;
        private int year;
        private static final String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        /**
         * Constructs a new {@code Date} object with numeric month, day, and year.
         *
         * @param month the month (1-12)
         * @param day   the day of the month
         * @param year  the year
         */
        public Date(int month, int day, int year) {
            this.month = month;
            this.day = day;
            this.year = year;
        }

        /**
         * Constructs a new {@code Date} object using the month's name, day, and year.
         *
         * @param month the name of the month (e.g., "January")
         * @param day   the day of the month
         * @param year  the year
         * @throws IllegalArgumentException if the specified month is invalid
         */
        public Date(String month, int day, int year) {
            int monthNumber = 0;
            for (int i = 0; i < months.length; i++) {
                if (months[i].equals(month)) {
                    monthNumber = i + 1;
                    break;
                }
            }
            if (monthNumber == 0)
                throw new IllegalArgumentException("Specified month is not a month!");
            this.month = monthNumber;
            this.day = day;
            this.year = year;
        }

        @Override
        public String toString() {
            return months[month - 1] + "/" + day + "/" + year;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Date))
                throw new IllegalArgumentException("Argument is not a Date");
            Date otherDate = (Date) other;
            return (month == otherDate.month && day == otherDate.day && year == otherDate.year);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + month;
            result = 31 * result + day;
            result = 31 * result + year;
            return result;
        }
    }
}
