import javax.swing.*;

public class View extends JFrame {
    private final Controller controller;
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
    private JLabel rest;

    public View() {
        this.controller = new Controller(this);

        {
            setContentPane(mainPanel);
            setTitle("Polynomial Calculator");
            setSize(450, 250);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setResizable(false);
            setVisible(true);
        }
        setResult(" ");
        setRest(" ");
        buttonAdd.addActionListener(controller);
        buttonSub.addActionListener(controller);
        buttonMul.addActionListener(controller);
        buttonDiv.addActionListener(controller);
        buttonInt.addActionListener(controller);
        buttonDer.addActionListener(controller);
    }

    public static void main(String[] args) {
        View myFrame = new View();
    }

    protected String getP1() {
        return textP1.getText();
    }

    protected String getP2() {
        return textP2.getText();
    }

    protected void setResult(String string) {
        if (string.equals("") || string.equals("D1:"))
            result.setText("0");
        else
            result.setText(string);
    }

    protected void setRest(String string) {
        if (string.equals("") || string.equals("D2:"))
            rest.setText("0");
        else
            rest.setText(string);
    }
}
