package calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

class UserInterface {
    void calculateFigure(String method){
        Scanner scanner = new Scanner(System.in);
        try {
            switch (method) {
                case "T":
                    System.out.println("Side A: ");
                    double sideA = scanner.nextDouble();
                    System.out.println("Side B: ");
                    double sideB = scanner.nextDouble();
                    System.out.println("Basis: ");
                    double basis = scanner.nextDouble();
                    System.out.println("Height: ");
                    double height = scanner.nextDouble();
                    if (sideA <= 0 || sideB <= 0 || basis <= 0 || height <= 0) {
                        throw new IllegalArgumentException("All values must be must be > 0");
                    }
                    Triangle triangle = new Triangle(sideA, sideB, basis, height);
                    triangle.print();
                    break;
                case "S":

                    System.out.println("Side A: ");
                    double side = scanner.nextDouble();
                    if (side <= 0)
                        throw new IllegalArgumentException("Side must be > 0");
                    Square square = new Square(side);
                    square.print();
                    break;
                case "C":
                    System.out.println("Radius: ");
                    double radius = scanner.nextDouble();
                    if (radius <= 0)
                        throw new IllegalArgumentException("Radius must be > 0");
                    Circle circle = new Circle(radius);
                    circle.print();
                    break;
                case "E":
                    System.exit(0);
                default:
                    System.out.println("You can choose only T,S,C or E");
                    break;
            }
        }
        catch(InputMismatchException e){
            System.out.println("It is not a number. It must be positive number.");
            calculateFigure(method);
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            calculateFigure(method);
    }
}
}