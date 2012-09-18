/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Adam
 */
public class PrimeCheck extends JPanel implements ActionListener {

    private JTextField textField;
    private JTextArea textArea;
    private JButton checkButton;
    private JButton clearButton;

    public PrimeCheck() {
        super(new GridBagLayout());
        textField = new JTextField(7);
        textArea = new JTextArea(5, 20);
        checkButton = new JButton("Check");
        checkButton.addActionListener(this);


        GridBagConstraints con = new GridBagConstraints();

        textField.addActionListener(this);
        con.gridx = 0;
        con.gridy = 0;
        con.anchor = GridBagConstraints.NORTHWEST;
        con.insets = new Insets(5, 5, 5, 5);
        add(textField, con);

        JLabel label1 = new JLabel("Enter an integer.");
        con.gridx = 1;
        con.gridy = 0;
        con.insets = new Insets(5, 5, 5, 5);

        add(label1, con);


        con.gridx = 0;
        con.gridy = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridwidth = GridBagConstraints.RELATIVE;
        add(checkButton, con);

        JLabel label2 = new JLabel("Click to see if number is prime");
        con.gridx = 1;
        con.gridy = 1;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.gridwidth = GridBagConstraints.RELATIVE;
        add(label2, con);


        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        con.gridx = 0;
        con.gridy = 2;
        con.weightx = 0.0;
        con.weighty = 0.500;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = GridBagConstraints.REMAINDER;
        add(scrollPane, con);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        con.weighty = 0.0; 
        add(clearButton, con);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     
     if (e.getSource()== clearButton)  {
         textArea.setText(""); 
     } 
     else {
        try {
            Integer number = Integer.parseInt(textField.getText());
            if (isPrime(number)) {
                textArea.append(number + " is prime." + "\n");
            } else {
                textArea.append(number + " is not prime." + "\n");
            }
        } 
         catch(NumberFormatException exception)  {
          
            textArea.append("The number you entered is not integer" + "\n");
        }
     }

    }

    public static void createandrunGUI() {
        JFrame frame = new JFrame("Check if the number is prime.");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocation(300, 400);
        frame.setResizable(false);
        frame.add(new PrimeCheck());

        Dimension defaultSize = new Dimension(300, 500);
        frame.setSize(defaultSize);
        frame.setVisible(true);
    }

    public static void main(String[] arg) {
        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        createandrunGUI();
                    }
                });



    }

   private static boolean isPrime(Integer number) {

        if (number == 0 || number == 1) {
            return false;
        }
        for (int i=2 ; i!=number; i++) {
            if(number%i==0) { 
                return false; 
            }
                  }

      return true; 

    }
}
