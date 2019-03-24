package App;

public class MathematicalFormula {
    private String name;
    private String formula;

    MathematicalFormula(String name, String formula) {
        this.name = name;
        this.formula = formula;
    }
    public String getName() {
        return name;
    }
    public String getFormula() {
        return formula;
    }
    @Override
    public String toString() {
        return name;
    }
}
