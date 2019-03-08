package calculator;

public class Square extends Figure implements Print{

    private final double side;
    
    Square(double side) {
        this.side = side;
    }
    
    @Override
    double calculateArea() {
        return side * side;
    }

    @Override
    double calculatePerimeter() {
        return 4* side;
    }

    @Override
    public void print() {
        System.out.println("Square with side = " + side + " Area: " 
                + calculateArea() + " Perimeter: " + calculatePerimeter());
    }
}
