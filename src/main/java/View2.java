import javax.swing.*;
import java.awt.*;

public class View2 extends JFrame {
    private JPanel pane = new JPanel(new GridBagLayout());

    GridBagConstraints c = new GridBagConstraints();

    private JButton button1 = new JButton("P1 + P2");
    private JButton button2 = new JButton("P1 - P2");
    private JButton button3 = new JButton("P1 * P2");
    private JButton button4 = new JButton("P1 / P2");

    private JTextField textP1 = new JTextField(20);
    private JTextField textP2 = new JTextField(20);

    private JLabel labelP1 = new JLabel("P1:");
    private JLabel labelP2 = new JLabel("P2:");

    public View2(String name) {
        super(name);

        c.gridy = 0; // FIRST ROW

        c.gridx = 0;
        c.gridwidth = 1;
        pane.add(labelP1,c);
        c.gridx = 1;
        c.gridwidth = 3;
        pane.add(textP1, c);

        c.gridy = 1; // SECOND ROW

        c.gridx = 0;
        c.gridwidth = 1;
        pane.add(labelP2, c);
        c.gridx = 1;
        c.gridwidth = 3;
        pane.add(textP2, c);

        //BUTTONS
        c.gridy = 2; // THIRD ROW
        //c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_END;
        pane.add(button1, c);
        c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        pane.add(button2, c);

        c.gridy = 3; // FOURTH ROW


        c.gridx = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_END;
        pane.add(button3, c);
        c.gridx = 2;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.LINE_START;
        pane.add(button4, c);



        this.add(pane);
    }

    public static void main(String[] args) {
        JFrame frame = new View2("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
