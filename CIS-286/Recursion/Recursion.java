/**
 * A class that recursively tests if an string is a substring of another string
 * @author Jordan Marcelo
 * @version 1.0
 * CIS 286 Professor Hoffman
 * 3/8/25
 */
public class Recursion
{
    /**
     * A method that tests if sub_str is a substring of full_str
     * 
     * @param full_str the full string to be seen if it contains sub_str
     * @param sub_str the string to be found inside of full_str
     * @return true if sub_str is a substring of full_str, false otherwise
     */
    public static boolean find(String full_str, String sub_str)
    {
        // check that both arguments are valid, and that full_str can be substringed again
        if (sub_str == null || full_str == null || sub_str.length() == 0 || full_str.length() < sub_str.length())
        {
            return false;
        }
        // if first letters in full_str match substring
        if (full_str.substring(0, sub_str.length()).equals(sub_str))
        {
            return true;
        }
        // check if subsequent letters in full_str match substring
        return find(full_str.substring(1), sub_str);
    }
}