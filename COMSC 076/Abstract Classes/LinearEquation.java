/**
 * A class representing a Linear Equation ax + b = 0
 * 
 * @author Jordan Marcelo
 * @version 1.0
 */
public class LinearEquation extends Equation {

    /**
     * Creates a linear equation with the given coefficients.
     * The coefficients are expected to be in the form of a (coefficient for x) and b (constant term).
     * 
     * @param a The coefficient for the variable x.
     * @param b The constant term.
     */
    public LinearEquation(double a, double b) {
        super(new double[] {b, a});
    }

    /**
     * Creates a linear equation with two coefficients.
     * 
     * @param coefficients An array of size 2 containing the coefficients, where coefficients[0] is the constant term and coefficients[1] is the coefficient for x.
     * @throws IllegalArgumentException If the coefficients array does not have exactly two elements.
     */
    public LinearEquation(double[] coefficients) {
        super(coefficients);
        if (coefficients.length != 2) {
            throw new IllegalArgumentException("Coefficient count mismatch.");
        }
    }

    /**
     * Finds the solution for the linear equation ax + b = 0. 
     * 
     * @return A double array containing the solution for x. If the equation has no solution, it returns null.
     * @throws UnsupportedOperationException If the equation has infinitely many solutions (0 = 0).
     */
    @Override
    public double[] findSolution() {
        double a = coefficients[1];
        double b = coefficients[0];
        
        // No solution if a == 0 and b != 0
        if (a == 0 && b != 0) {
            return null;
        }
        // Infinitely many solutions if a == 0 and b == 0
        if (a == 0 && b == 0) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        // Solve for x when a != 0
        double x = -b/a;
        return new double[] {x};
    }

    /**
     * Returns a string representation of the linear equation in the form of "ax + b = 0".
     * 
     * @return A string representing the linear equation in the form of "ax + b = 0".
     */
    @Override
    public String toString() {
        double a = coefficients[1];
        double b = coefficients[0];

        String aString = String.format("%.2f", a);
        String bString = String.format("%.2f", b);

        return aString + "x + " + bString + " = 0";
    }
}
