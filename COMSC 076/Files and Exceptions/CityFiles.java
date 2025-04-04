import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  
 * @author Balaji Srinivasan, Jordan Marcelo
 */
public class CityFiles {

    /**
     * The status for the sortPopulation operation.
     */
    public enum Status {
        SUCCESS, // Returned when operation completes successfully.
        INPUT_FILE_EXCEPTION, // Returned when input file could not be opened.
        OUTPUT_FILE_EXCEPTION, // Returned if output file could not be opened or written to.
        BADLY_FORMATTED_LINE // Returned when the line is not formatted correctly in the input file
    };

    /**
     * Processes the given input file and sorts the cities in alphabetical order.
     * @param in The input file name.
     * @param out The output file name.
     * @return The return status which should be one of the enum values above.
     */
    public static Status sortPopulation(String in, String out) {
        // create an ArrayList to store each city object to be sorted later
        ArrayList<City> cityArray = new ArrayList<>();

        // try statement catches exceptions in the following code
        try (BufferedReader reader = new BufferedReader(new FileReader(in));) {
            // read first line
            String currentLine = reader.readLine();
            // continue as long as there is a next line
            while (currentLine != null) {
                // separate line into array of attributes
                // each element is separated by a comma
                String[] attributes = currentLine.split(",");
                // ensures each line only has 3 entries
                if (attributes.length != 3) {
                    return Status.BADLY_FORMATTED_LINE;
                }
                // name and state can be any string
                String name = attributes[0];
                String state = attributes[1];
                // population must be an int and the last entry
                int population = Integer.parseInt(attributes[2]);
                // create City instance with newfound attributes
                City newCity = new City(name, state, population);
                // add the city to the aforementioned ArrayList to be sorted
                cityArray.add(newCity);
                // advance to the next line of the input file
                currentLine = reader.readLine();
            }
            // stop memory leaks from reader
            reader.close();
        } catch (IOException e) {
            // can't read/open file
            return Status.INPUT_FILE_EXCEPTION;
        } catch (NumberFormatException e) {
            // population entry (third) can't be parsed as an integer
            return Status.BADLY_FORMATTED_LINE;
        }
        // sort the cities in ascending order by population
        // based on the CompareTo method in the city class
        Collections.sort(cityArray);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(out));) {
            // write each city's string representation on a new line in the output file
            // based on City class' ToString method, with the following format:
            // name, state, population
            for (City city : cityArray) {
                writer.write(city.toString());
                writer.newLine();
            }
            // prevent memory leaks from the BufferedWriter being open
            // per BufferedWriter documentation, automatically flushes before closing
            writer.close();
        } catch (IOException e) {
            // can't write output file, eg. invalid name
            return Status.OUTPUT_FILE_EXCEPTION;
        }
        // all has worked well if no return or catch statements have triggered yet
        return Status.SUCCESS;
    }
}
