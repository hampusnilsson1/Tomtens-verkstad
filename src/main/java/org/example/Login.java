package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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



        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = användarField.getText();
                String enteredPassword = lösenordField.getText();


                // läser användarnam och lösenord
                Map<String, String> userCredentials = readUserCredentials("Kids.csv");

                // Kollar ifall det man har skrivit in i fälten matchar vad som finns i Kids.csv
                if (userCredentials.containsKey(enteredUsername) &&
                        userCredentials.get(enteredUsername).equals(enteredPassword)) {
                    Wishlist wishlist = new Wishlist(enteredUsername);
                } else {
                    System.out.println("Fel Användarnamn eller Lösenord!");
                }
            }

            private Map<String, String> readUserCredentials(String filePath) {
                Map<String, String> userCredentials = new HashMap<>();

                try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            String username = parts[1].trim();
                            String password = parts[2].trim();
                            userCredentials.put(username, password);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return userCredentials;
            }

        });
    }


}
