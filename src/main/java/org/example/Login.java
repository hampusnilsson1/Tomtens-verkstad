package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Login {
    JFrame logInFrame;
    private JPanel loginPanel;
    private JTextField userField;
    private JCheckBox iHaveBeenGoodCheckBox;
    private JButton logInButton;
    private JButton registreraButton;
    private JLabel loginMessage;
    private JPasswordField passwordField1;
    private String adminUserName = "Julen";
    private String adminPassword = "2023";
    HashMap<String, String> userData = new HashMap<>();



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
             String Username = userField.getText();
             String Password = new String(passwordField1.getPassword());
              if (Username.equals(adminUserName) && Password.equals(adminPassword)) {
                  new TomtensView();

              } else if(userData.containsKey(Username) && userData.get(Username).equals(Password)) {
                 if (iHaveBeenGoodCheckBox.isSelected()) {

                 } else {
                    loginMessage.setText("Vänligen kryssa i 'Jag har varit snäll' för att logga in!");
                     userField.setText("");
                     passwordField1.setText("");
                 }
             }else{
                 loginMessage.setText("Fel inloggningsuppgifter!");
                 userField.setText("");
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
