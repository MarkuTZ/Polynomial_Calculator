import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Controller implements ActionListener {
    private final View view;

    public Controller(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String p1String = view.getP1();
        String p2String = view.getP2();
        Polynomial p1, p2;
        JButton buttonPressed = (JButton) e.getSource();
        view.setRest(" ");
        switch (buttonPressed.getText()) {
            case "Addition" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    view.setResult("Enter both polynomials!!");
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult(Operations.addPolynomials(p1, p2).toString());
            }
            case "Subtraction" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    view.setResult("Enter both polynomials!!");
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult(Operations.subPolynomials(p1, p2).toString());
            }
            case "Multiplication" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    view.setResult("Enter both polynomials!!");
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult(Operations.mulPolynomials(p1, p2).toString());
            }
            case "Division" -> {
                if (p1String.equals("") || p2String.equals("")) {
                    view.setResult("Enter both polynomials!!");
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                LinkedList<Polynomial> CR = Operations.divPolynomials(p1, p2);
                view.setResult("Q:" + CR.getFirst().toString());
                view.setRest("R:" + CR.getLast().toString());
            }
            case "Derivation" -> {
                if (p1String.equals("") && p2String.equals("")) {
                    view.setResult("Enter at least one polynomial!!");
                    break;
                }
                p1 = Operations.toPolynomial(p1String);
                p2 = Operations.toPolynomial(p2String);
                view.setResult("D1:" + ((p1String.equals("")) ? "Polynomial not introduced! " : Operations.derPolynomial(p1)));
                view.setRest("D2:" + ((p2String.equals("")) ? "Polynomial not introduced! " : Operations.derPolynomial(p2)));
            }
            case "Integration" -> {
                if (p1String.equals("") && p2String.equals("")) {
                    view.setResult("Enter at least one polynomial!!");
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
