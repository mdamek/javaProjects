package App;
import org.mariuszgromada.math.mxparser.Expression;

public class ExpressionsParser {
    public static String LastExpression = "";
    public static String LastAnswer = "";

    public static MathAction Parse(String action) {
        Double answer;
        Expression expression = new Expression(action);
        if (expression.checkSyntax()) {
            answer =  expression.calculate();
            LastExpression = action;
            LastAnswer = answer.toString();
        }
        else {
            String errorMessage = expression.getErrorMessage();
            throw new IllegalArgumentException(errorMessage);
        }
        return new MathAction(action, answer);


    }
}
