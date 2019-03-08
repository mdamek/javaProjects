package calculator;

public class Circle extends Figure implements Print{

    private final double radius;
    Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter() {
        return 2 * Math.PI * radius; 
    }

    @Override
    public void print() {
        System.out.println("Circle with r = " + radius + " Area: " 
                + calculateArea() + " Perimeter: " + calculatePerimeter());
    }
    
}
