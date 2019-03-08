package calculator;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        while (true){
            System.out.println("Calculate: ");
            System.out.println("T - Triangle, S - Square, C - Circle, E - Exit");
            String figure = scanner.nextLine();
            UserInterface userInterface = new UserInterface();
            userInterface.calculateFigure(figure);
        }
    }        
}