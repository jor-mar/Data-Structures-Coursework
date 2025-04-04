/**
 * A class representing a triangle
 * 
 * @author Jordan Marcelo
 * @version 1.0
 */
class Triangle implements GeometricObject {
    private double leg1;
    private double leg2;
    private double leg3;

    /**
     * Creates a triangle with the given leg lengths
     * 
     * @param leg1 the length of the first leg of the triangle
     * @param leg2 the length of the second leg of the triangle
     * @param leg3 the length of the third leg of the triangle
     * @throws RuntimeException if the leg dimensions are not strictly positive or are geometrically impossible
     */
    public Triangle(double leg1, double leg2, double leg3) {
        // Negative dimensions do not make geometric sense.
        if (leg1 <= 0 || leg2 <= 0 || leg3 <= 0) {
            throw new RuntimeException("Dimensions must be strictly positive.");
        }

        // Triangle inequality theorem:
        // The sum of two side lengths is always greater than the third side length.
        if (leg1 + leg2 < leg3 || leg2 + leg3 < leg1 || leg1 + leg3 < leg2) {
            throw new RuntimeException("Dimensions are not possible.");
        }
        
        this.leg1 = leg1;
        this.leg2 = leg2;
        this.leg3 = leg3;
    }

    /**
     * Calculates the area of the triangle
     * 
     * @return a double representing the area of the triangle
     */
    @Override
    public double getArea() {
        // Heron's formula: A = (S(S-A)(S-B)(S-C))^(1/2)
        // S = semiperimeter = (A+B+C)/2
        double semiperimeter = getPerimeter() / 2;
        double radicand = semiperimeter;

        double[] leglengths = new double[] {leg1, leg2, leg3};
        for (double leg : leglengths) {
            radicand *= semiperimeter-leg;
        }

        return Math.sqrt(radicand);
    }

    /**
     * Calculates the perimeter of the triangle
     * 
     * @return a double representing the perimeter of the triangle
     */
    @Override
    public double getPerimeter() {
        return leg1 + leg2 + leg3;
    }
}