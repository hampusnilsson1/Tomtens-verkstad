package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;

public class Login {
    JFrame logInFrame;
    private JPanel loginPanel;
    private JTextField användarField;
    private JCheckBox jagHarVaritSnällCheckBox;
    private JButton logInButton;
    private JButton registreraButton;
    private JLabel loginMessage;
    private JPasswordField passwordField1;
    private String adminAnvändarnamn = "Julen";
    private String adminLösenord = "2023";
    HashMap<String, String> användarUppgifter = new HashMap<>();



    public Login()
    {
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logInFrame.setSize(500,500);
        logInFrame.setContentPane(loginPanel);
        logInFrame.setResizable(false);
        logInFrame.setVisible(true);

        logInButton.addActionListener(new ActionListener() {
         @Override
          public void actionPerformed(ActionEvent e) {
             String användarnamn = användarField.getText();
             String lösenord = new String(passwordField1.getPassword());
              if (användarnamn.equals(adminAnvändarnamn) && lösenord.equals(adminLösenord)) {
                  loginMessage.setText ("Fel inloggningsuppgifter!");
                  new TomtensView();

              } else if(användarUppgifter.containsKey(användarnamn) && användarUppgifter.get(användarnamn).equals(lösenord)) {
                 if (jagHarVaritSnällCheckBox.isSelected()) {
                     new TomtensView();

                 } else {
                    loginMessage.setText("Vänligen kryssa i 'Jag har varit snäll' för att logga in!");
                     användarField.setText("");
                     passwordField1.setText("");
                 }
             }else{
                 loginMessage.setText("Fel inloggningsuppgifter!");
                 användarField.setText("");
                 passwordField1.setText("");
             }

          }
        });
        registreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

    }
}
