/**
 * A class representing a rectangle with a length, width, and height
 * 
 * @author Jordan Marcelo
 * @version 1.0
 */
class Rectangle implements GeometricObject {
    private double height;
    private double width;

    /**
     * Creates a rectangle with the given dimensions
     * 
     * @param width the width of the rectnagle
     * @param height the height of the rectangle
     * @throws RuntimeException if the rectangle is created with a negative dimension
     */
    public Rectangle(double width, double height) {
        if (height <= 0 || width <= 0) {
            throw new RuntimeException("Dimensions must be strictly positive.");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * Calculates the area of the rectangle
     * 
     * @return a double representing the rectangle's area
     */
    @Override
    public double getArea() {
        return height*width;
    }

    /**
     * Calculates the perimeter of the rectangle
     * 
     * @return a double representing the rectangle's perimeter
     */
    @Override
    public double getPerimeter() {
        return 2 * (height + width);
    }
} 
