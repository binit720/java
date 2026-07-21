import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class calculator { 

    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

       
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel l1 = new JLabel("First Number:");
        JLabel l2 = new JLabel("Second Number:");

        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();

        inputPanel.add(l1);
        inputPanel.add(t1);
        inputPanel.add(l2);
        inputPanel.add(t2);

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton add = new JButton("Add");
        JButton sub = new JButton("Subtract");
        JButton mul = new JButton("Multiply");
        JButton div = new JButton("Divide");

        buttonPanel.add(add);
        buttonPanel.add(sub);
        buttonPanel.add(mul);
        buttonPanel.add(div);

       
        JPanel resultPanel = new JPanel();

        JLabel resultLabel = new JLabel("Result: ");
        resultPanel.add(resultLabel);

       
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(resultPanel, BorderLayout.SOUTH);

     
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                double n1 = Double.parseDouble(t1.getText());
                double n2 = Double.parseDouble(t2.getText());

                resultLabel.setText("Result: " + (n1 + n2));
            }
        });

        sub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                double n1 = Double.parseDouble(t1.getText());
                double n2 = Double.parseDouble(t2.getText());

                resultLabel.setText("Result: " + (n1 - n2));
            }
        });

        mul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                double n1 = Double.parseDouble(t1.getText());
                double n2 = Double.parseDouble(t2.getText());

                resultLabel.setText("Result: " + (n1 * n2));
            }
        });

       
        div.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                double n1 = Double.parseDouble(t1.getText());
                double n2 = Double.parseDouble(t2.getText());

                resultLabel.setText("Result: " + (n1 / n2));
            }
        });

        frame.setVisible(true);
    }
}