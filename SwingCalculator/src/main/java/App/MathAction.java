package App;

public class MathAction {

    private String Action;
    private Double Result;


    MathAction(String action, Double result) {
        Action = action;
        Result = result;
    }

    public String getAction() {
        return Action;
    }

    public double getResult() {
        return Result;
    }

}
