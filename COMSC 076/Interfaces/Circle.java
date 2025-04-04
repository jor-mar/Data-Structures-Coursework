/**
 * A class representing a circle with a radius.
 * 
 * @author Jordan Marcelo
 * @version 1.0 
 */
class Circle implements GeometricObject {
    private double radius;

    /**
     * Creates a circle with the given radius
     * 
     * @param radius the radius of the circle
     * @throws RuntimeException if radius is not strictly positive
     */
    public Circle(double radius) {
        if (radius <= 0) {
            throw new RuntimeException("Radius must be strictly positive.");
        }
        this.radius = radius;
    }

    /**
     * Calculates the circle's area
     * 
     * @return a double representing the circle's area
     */
    @Override
    public double getArea() {
        return Math.PI*radius*radius;
    }

    /**
     * Calculates the circle's perimeter
     * 
     * @return a double representing the circle's perimeter
     */
    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }
}