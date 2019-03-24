package App;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class GeneralLayout extends JFrame {

    private static JMenuBar menuBar;
    private static JMenu menu;
    private static JMenuItem menuItem1, menuItem2;
    private JPanel mainPanel;
    private JTextArea historyTextArea;
    private JButton evalButton;
    private JTextField formulaInput;
    private JScrollPane scrollContainerPane;
    private JList<MathematicalFormula> functionsList;

    GeneralLayout() {

        SetupMenu();
        SetupGlobalSettings();
        this.add( mainPanel );

        GenerateMathematicalFormulaList();



        menuItem1.addActionListener( e -> {
            historyTextArea.setText( "" );
            formulaInput.setText( "" );
        } );
        menuItem2.addActionListener( e -> System.exit( 0 ) );
        evalButton.addActionListener( e -> ProcessMathAction() );
        formulaInput.addKeyListener( new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    ProcessMathAction();
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    formulaInput.setText( ExpressionsParser.LastExpression );
            }
        } );
        functionsList.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList list = (JList)e.getSource();
                if(e.getClickCount() == 2){
                    int index = list.locationToIndex(e.getPoint());
                    MathematicalFormula formula = (MathematicalFormula) list.getModel().getElementAt( index );
                    if(formula.getName().equals( "Last result" )){
                        formulaInput.setText(formulaInput.getText() + ExpressionsParser.LastAnswer );
                    }
                    else{
                        formulaInput.setText(formulaInput.getText() + formula.getFormula() );
                    }
                    String actualText = formulaInput.getText();
                    SetCorrectPositionOfCaret( actualText );
                }

            }
        } );
    }

    private void ProcessMathAction() {
        try{
            MathAction mathAction = ExpressionsParser.Parse( formulaInput.getText() );
            historyTextArea.append( MessageFormat.format( "{0}"
                            + System.lineSeparator() +
                            "                              = {1} "
                            + System.lineSeparator() + "----------------"
                            + System.lineSeparator(),
                    mathAction.getAction(), mathAction.getResult() ) );
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Illegal argument!",
                    JOptionPane.ERROR_MESSAGE);
        }
        finally {
            formulaInput.setText( "" );
        }
    }

    private void SetCorrectPositionOfCaret(String text){
        List<Integer> closingsBrackets = new ArrayList<>();
        for ( int i = 0; i < formulaInput.getText().length(); i ++ ){
            if(text.charAt( i ) ==')')
                closingsBrackets.add( i );
        }
        if(closingsBrackets.size() > 0)
            formulaInput.setCaretPosition( closingsBrackets.get( closingsBrackets.size() -1 ));
        else
            formulaInput.setCaretPosition( formulaInput.getText().length() );
        formulaInput.requestFocusInWindow();

    }

    private void SetupMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu( "Options" );
        menuItem1 = new JMenuItem( "Reset" );
        menuItem2 = new JMenuItem( "Exit" );
        menu.add( menuItem1 );
        menu.add( menuItem2 );
        menuBar.add( menu );
        this.setJMenuBar( menuBar );
    }

    private void SetupGlobalSettings() {
        this.setTitle( "My calculator" );
        this.setSize( 700, 700 );
        historyTextArea.setEditable( false );
    }

    private void GenerateMathematicalFormulaList(){

        MathematicalFormula sin = new MathematicalFormula("sin", "sin()");
        MathematicalFormula cos = new MathematicalFormula("cos", "cos()");
        MathematicalFormula ln = new MathematicalFormula("ln", "ln()");
        MathematicalFormula tg = new MathematicalFormula("tg", "tg()");
        MathematicalFormula ctg = new MathematicalFormula("ctg", "ctg()");

        MathematicalFormula pi = new MathematicalFormula("pi", "pi");
        MathematicalFormula e = new MathematicalFormula("e", "e");
        MathematicalFormula phi = new MathematicalFormula("Golden ratio", "[phi]");

        MathematicalFormula exponentiation = new MathematicalFormula("Exponentiation", "^");
        MathematicalFormula factorial = new MathematicalFormula("Factorial", "!");
        MathematicalFormula modulo = new MathematicalFormula("Modulo", "#");
        MathematicalFormula lastResult = new MathematicalFormula( "Last result", "" );

        DefaultListModel<MathematicalFormula> formulas = new DefaultListModel<>();
        formulas.addElement(sin);
        formulas.addElement(cos);
        formulas.addElement(ln);
        formulas.addElement(tg);
        formulas.addElement(ctg);
        formulas.addElement(pi);
        formulas.addElement(e);
        formulas.addElement(phi);
        formulas.addElement(exponentiation);
        formulas.addElement(factorial);
        formulas.addElement(modulo);
        formulas.addElement(lastResult);
        functionsList.setModel( formulas );
    }


}

