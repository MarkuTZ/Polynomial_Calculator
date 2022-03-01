import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
    private JPanel mainPanel;
    private JPanel panelPolynomials;
    private JPanel panelOperations;
    private JPanel panelResult;

    private JButton buttonAdd;
    private JButton buttonSub;
    private JButton buttonMul;
    private JButton buttonDiv;
    private JButton buttonInt;
    private JButton buttonDer;

    private JTextField textP1;
    private JTextField textP2;

    private JLabel labelP1;
    private JLabel labelP2;
    private JLabel result;

    private Controller controller;
    public View() {
        this.controller = new Controller(this);

        {
            setContentPane(mainPanel);
            setTitle("Polynomial Calculator");
            setSize(450,250);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setVisible(true);
        }
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.AddPolynomials();
            }
        });
    }
    protected String getP1() {
        return textP1.getText();
    }
    protected String getP2() {
        return textP2.getText();
    }
    protected void setResult(String string) {
        result.setText(string);
    }

    public static void main(String[] args) {
        View myFrame = new View();
    }
}
