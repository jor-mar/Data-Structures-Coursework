/**
 * A class representing a quadratic equation ax^2 + bx + c = 0
 * 
 * @author Jordan Marcelo
 * @version 1.0
 */

public class QuadraticEquation extends Equation {
    /**
     * Creates a quadratic equation ax^2 + bx + c = 0 with the given coefficients.
     * 
     * @param a The coefficient for the variable x^2.
     * @param b The coefficient for the variable x.
     * @param c The constant term.
     */
    public QuadraticEquation(double a, double b, double c) {
        super(new double[] {c, b, a});
    }

    /**
     * Creates a quadratic equation ax^2 + bx + c with the given coefficients.
     * 
     * @param coefficients An array of size 3 containing the coefficients, where:
     *                     coefficients[0] is the constant term,
     *                     coefficients[1] is the coefficient for x,
     *                     coefficients[2] is the coefficient for x^2.
     * @throws IllegalArgumentException If the coefficients array does not have exactly three elements.
     */
    public QuadraticEquation(double[] coefficients) {
        super(coefficients);
        if (coefficients.length != 3) {
            throw new IllegalArgumentException("Coefficient count mismatch.");
        }
    }

    /**
     * Finds real solution(s) for a quadratic equation ax^2 + bx + c = 0.
     * 
     * @return An array of size matching the number of solutions, containing the solution(s) for x. 
     * @throws UnsupportedOperationException If the equation has complex solutions (discriminant < 0).
     */
    @Override
    public double[] findSolution() {
        double a = coefficients[2];
        double b = coefficients[1];
        double c = coefficients[0];
        double discriminant = b*b - 4*a*c;

        // imaginary solutions
        if (discriminant < 0) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        // bx + c = 0
        if (a == 0) {
            LinearEquation lineq = new LinearEquation(b, c);
            return lineq.findSolution();
        }

        double sol1 = (-b + Math.sqrt(discriminant)) / (2*a);
        double sol2 = (-b - Math.sqrt(discriminant)) / (2*a);
        if (sol1 == sol2) {
            return new double[] {sol1};
        }
        return new double[] {sol1, sol2};
    }

    /**
     * Returns a string representation of the quadratic equation in the form of "ax^2 + bx + c = 0".
     * 
     * @return A string representing the quadratic equation in the form "ax^2 + bx + c = 0".
     */
    @Override
    public String toString() {
        // ax^2 + bx + c = 0
        double a = coefficients[2];
        double b = coefficients[1];
        double c = coefficients[0];

        String aString = String.format("%.2f", a);
        String bString = String.format("%.2f", b);
        String cString = String.format("%.2f", c);

        return aString + "x\u00b2 + " + bString + "x + " + cString + " = 0";
    }
}
