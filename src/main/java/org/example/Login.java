package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Map<String, String> readUserCredentials(String filePath) {
        Map<String, String> userCredentials = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[1].trim();
                    String password = parts[2].trim();
                    if(!username.equals("name")){
                        Kid kid = new Kid(username,password);
                        userCredentials.put(username, password);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userCredentials;
    }


    public Login()
    {
        logInFrame = new JFrame();
        logInFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        logInFrame.setSize(500,500);
        logInFrame.setContentPane(loginPanel);
        logInFrame.setResizable(false);
        logInFrame.setVisible(true);

        //Läser in alla barn som finns att logga in på
        Map<String, String> userCredentials = readUserCredentials("Kids.csv");

        registreraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register(userCredentials);
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = userField.getText();
                String enteredPassword = passwordField1.getText();

                if (enteredUsername.equals(adminUserName) && enteredPassword.equals(adminPassword)) {
                    new TomtensView();
                    logInFrame.dispose();
                } else if (userCredentials.containsKey(enteredUsername) &&
                        userCredentials.get(enteredUsername).equals(enteredPassword) && iHaveBeenGoodCheckBox.isSelected()) {
                    Wishlist wishlist = new Wishlist(enteredUsername);
                    logInFrame.dispose();
                } else if (!iHaveBeenGoodCheckBox.isSelected()){
                    loginMessage.setText("Vänligen kryssa i 'Jag har varit snäll' för att logga in!");
                } else {
                    loginMessage.setText("Fel inloggningsuppgifter!");
                    userField.setText("");
                    passwordField1.setText("");
                }
            }
        });
    }


}
