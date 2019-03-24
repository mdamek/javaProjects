package App;

import com.sun.javaws.exceptions.InvalidArgumentException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;

public class GeneralLayout extends JFrame {

    private static JMenuBar menuBar;
    private static JMenu menu;
    private static JMenuItem menuItem1, menuItem2;
    private JPanel mainPanel;
    private JTextArea historyTextArea;
    private JButton evalButton;
    private JTextField formulaInput;
    private JScrollPane scrollContainerPane;
    private JList functionsList;

    GeneralLayout() {

        SetupMenu();
        SetupGlobalSettings();
        this.add( mainPanel );
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


}

