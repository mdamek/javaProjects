package calculator;

public class Triangle extends Figure implements Print {

    private final double sideA;
    private final double sideB;
    private final double basis;
    private final double height;

    Triangle(double sideA, double sideB, double basis, double height) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.basis = basis;
        this.height = height;
    }
    
    @Override
    double calculateArea() {
        return basis * height;
    }

    @Override
    double calculatePerimeter() {
        return sideA + sideB + basis;
    }

    @Override
    public void print() {
        System.out.println("Triangle with basis = " + basis +" SideA: "+ sideA +
                " SideB " + sideB + " Area: " +
                calculateArea() + " Perimeter: " + calculatePerimeter());
    }
    
}
