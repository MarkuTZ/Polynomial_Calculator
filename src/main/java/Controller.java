import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements ActionListener {
    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String p1String = view.getP1();
        String p2String = view.getP2();

        Pattern pattern = Pattern.compile("[^xX0-9\\+\\-\\^\\.\\,\s]"); //Looking for other characters than the ones allowed
        Matcher matcher1 = pattern.matcher(p1String);
        Matcher matcher2 = pattern.matcher(p2String);
        if (matcher1.find() || matcher2.find()) {
            JOptionPane.showMessageDialog(view.getContentPane(),
                    "Enter valid polynomials!",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Polynomial p1, p2;
        JButton buttonPressed = (JButton) e.getSource();
        view.setRest(" ");
        switch (buttonPressed.getText()) {
            case "Addition" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Enter both polynomials!!",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult(Operations.addPolynomials(p1, p2).toString());
            }
            case "Subtraction" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Enter both polynomials!!",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult(Operations.subPolynomials(p1, p2).toString());
            }
            case "Multiplication" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Enter both polynomials!!",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult(Operations.mulPolynomials(p1, p2).toString());
            }
            case "Division" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Enter both polynomials!!",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                if (p2.getDegree() == -1) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Cannot divide by 0!!",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                }

                LinkedList<Polynomial> CR = Operations.divPolynomials(p1, p2);
                view.setResult("Q:" + CR.getFirst().toString());
                view.setRest("R:" + CR.getLast().toString());
            }
            case "Derivative" -> {
                if (p1String.equals("") && p2String.equals("")) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Enter at least one polynomial!!",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult("D1:" + ((p1String.equals("")) ? "Polynomial not introduced! " : Operations.derPolynomial(p1)));
                view.setRest("D2:" + ((p2String.equals("")) ? "Polynomial not introduced! " : Operations.derPolynomial(p2)));
            }
            case "Integral" -> {
                if (p1String.equals("") && p2String.equals("")) {
                    JOptionPane.showMessageDialog(view.getContentPane(),
                            "Enter at least one polynomial!!",
                            "ERROR",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult("I1:" + ((p1String.equals("")) ? "Polynomial not introduced! " : Operations.intPolynomial(p1)));
                view.setRest("I2:" + ((p2String.equals("")) ? "Polynomial not introduced! " : Operations.intPolynomial(p2)));
            }
        }
    }
}
