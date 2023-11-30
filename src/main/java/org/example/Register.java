package org.example;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Register {

    JFrame RegisterFrame;
    JTextField UsernameField;
    JPasswordField PasswordField;
    JPasswordField RePasswordField;
    Map<String, String> KidHashMap ;


    public Register(Map<String, String> userCredentials ) {
        KidHashMap = userCredentials ;
        RegisterFrame = new JFrame("KontoRegistrering");
        UsernameField = new JTextField();
        PasswordField = new JPasswordField();
        RePasswordField = new JPasswordField();

        JLabel UsernameLabel = new JLabel("Användarnamn:");
        JLabel PasswordLabel = new JLabel("Lösenord:");
        JLabel RePasswordLabel = new JLabel("Repetera Lösenord:");
        JButton RegisterButton = new JButton("Registera");
        ActionListener RegisterAL = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String Username = UsernameField.getText();
                String Password = new String(PasswordField.getPassword());
                String RePassword = new String(RePasswordField.getPassword());

                if(Username.length() < 2) {
                    System.out.println("Username must be 3 characters or longer.");
                    //Check = false;
                }
                else if(!(KidHashMap.get(Username) == null)) {
                    System.out.println("Username already taken");
                    //Check = false;
                }
                else if(Password.length() < 8) {
                    System.out.println("Password must be at least 8 characters long");
                    //Check = false;
                }
                else if(Password.equals(RePassword)) {
                    Kid kid = new Kid(Username, Password);
                    KidHashMap.put(Username, Password);
                    addToCSV(kid);

                    System.out.println("Successful registration");
                    System.out.println("id: " + kid.getKidId());
                    System.out.println("name: " + kid.getKidName());
                    System.out.println("PW: " + Password);
                    PasswordField.setText("");
                    UsernameField.setText("");
                    RePasswordField.setText("");

                } 
                else {
                    System.out.println("Passwords do not match");
                }
            }

        };
        RegisterButton.addActionListener(RegisterAL);

        JPanel RegisterPanel = new JPanel();
        RegisterPanel.setLayout(new GridLayout(4,2,10,10));
        RegisterPanel.add(UsernameLabel);
        RegisterPanel.add(UsernameField);
        RegisterPanel.add(PasswordLabel);
        RegisterPanel.add(PasswordField);
        RegisterPanel.add(RePasswordLabel);
        RegisterPanel.add(RePasswordField);
        RegisterPanel.add(new JLabel()); 
        RegisterPanel.add(RegisterButton);
        
        RegisterFrame.getContentPane().add(RegisterPanel);
        RegisterFrame.setSize(400, 150);
        RegisterFrame.setResizable(false);
        RegisterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        RegisterFrame.setVisible(true);
    }

    private void addToCSV(Kid kid) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Kids.csv", true))) {
            writer.write(kid.getKidId() + "," + kid.getKidName() + "," + kid.getKidPassword());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}